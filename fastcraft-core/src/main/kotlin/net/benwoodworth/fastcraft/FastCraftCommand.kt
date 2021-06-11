package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.api.FastCraftPreferences
import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.platform.command.FcCommand
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*
import javax.inject.Inject

class FastCraftCommand @Inject constructor(
    private val fcCommandRegistry: FcCommandRegistry,
    private val fcTextFactory: FcText.Factory,
    private val fcPlayerProvider: FcPlayer.Provider,
    private val fastCraftPreferences: FastCraftPreferences,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
    private val permissions: Permissions,
    private val fastCraftConfig: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
) : FcCommand,
    FcPlayer.Operations by fcPlayerOperations {

    override val description = "FastCraft command"

    override val usage = "/fastcraft (set|craft|reload) ..."
    private val usageSet = "/fastcraft set (enabled) <option> [<player>]"
    private val usageSetEnabled = "/fastcraft set enabled (true|false|toggle) [<player>]"
    private val usageCraft = "/fastcraft craft [fastcraft|grid|default] [<player>]"
    private val usageReload = "/fastcraft reload"

    private fun FcCommandSource.sendMissingPermissionMessage(permission: FcPermission) {
        sendMessage(fcTextFactory.create(
            text = Strings.commandErrorPermission(locale, permission),
        ))
    }

    private fun FcCommandSource.sendMustBePlayerMessage() {
        sendMessage(fcTextFactory.create(
            text = Strings.commandErrorPlayerOnly(locale),
        ))
    }

    private fun FcCommandSource.sendPlayerNotFoundMessage(player: String) {
        sendMessage(fcTextFactory.create(
            text = Strings.commandErrorPlayerUnknown(locale, player),
        ))
    }

    private fun FcCommandSource.sendUsageMessage(usage: String) {
        sendMessage(fcTextFactory.create(
            text = Strings.commandErrorUsage(locale, usage),
        ))
    }

    private val argSplitExpr = Regex("""\s+""")

    fun register() {
        fcCommandRegistry.register(this, "fastcraft", "fc")
    }

    override fun process(source: FcCommandSource, arguments: String) {
        val args = arguments.split(argSplitExpr)

        when (args.getOrNull(0)?.lowercase(Locale.getDefault())) {
            "set" -> when (args.getOrNull(1)?.lowercase(Locale.getDefault())) {
                "enabled" -> when (val enabled = args.getOrNull(2)?.lowercase(Locale.getDefault())) {
                    "true",
                    "false",
                    "toggle",
                    -> when (val player = args.getOrNull(3)) {
                        null -> when (enabled) {
                            "toggle" -> fcSetEnabled(source, toggle = true)
                            else -> fcSetEnabled(source, enabled = enabled.toBoolean())
                        }
                        else -> when (args.getOrNull(4)) {
                            null -> when (enabled) {
                                "toggle" -> fcSetEnabledAdmin(source, player, toggle = true)
                                else -> fcSetEnabledAdmin(source, player, enabled = enabled.toBoolean())
                            }
                            else -> source.sendUsageMessage(usageSetEnabled)
                        }
                    }
                    else -> {
                        source.sendUsageMessage(usageSetEnabled)
                    }
                }
                else -> {
                    source.sendUsageMessage(usageSet)
                }
            }
            "craft" -> when (val type = args.getOrNull(1)?.lowercase(Locale.getDefault()) ?: "default") {
                "fastcraft",
                "grid",
                "default",
                -> when (val player = args.getOrNull(2)) {
                    null -> {
                        fcCraft(source, type)
                    }
                    else -> when (args.getOrNull(3)) {
                        null -> {
                            fcCraftAdmin(source, type, player)
                        }
                        else -> {
                            source.sendUsageMessage(usageCraft)
                        }
                    }
                }
                else -> {
                    source.sendUsageMessage(usageCraft)
                }
            }
            "reload" -> when (args.getOrNull(1)?.lowercase(Locale.getDefault())) {
                null -> {
                    fcReload(source)
                }
                else -> {
                    source.sendUsageMessage(usageReload)
                }
            }
            else -> {
                source.sendUsageMessage(usage)
            }
        }
    }

    override fun getSuggestions(source: FcCommandSource, arguments: String): List<String> {
        val splitArguments = arguments.split(argSplitExpr)
        val args = splitArguments.dropLast(1)
        val argInProgress = splitArguments.last()

        fun suggestions(vararg suggestion: String): List<String> {
            return suggestion
                .filter { it.lowercase(Locale.getDefault()).startsWith(argInProgress.lowercase(Locale.getDefault())) }
                .toList()
        }

        fun suggestPlayers(): List<String> {
            val players = fcPlayerProvider
                .getOnlinePlayers()
                .map { it.username }
                .toTypedArray()

            return suggestions(*players)
        }

        return when (args.getOrNull(0)?.lowercase(Locale.getDefault())) {
            null -> suggestions("set", "craft", "reload")
            "set" -> when (args.getOrNull(1)?.lowercase(Locale.getDefault())) {
                null -> suggestions("enabled")
                "enabled" -> when (args.getOrNull(2)?.lowercase(Locale.getDefault())) {
                    null -> suggestions("true", "false", "toggle")
                    else -> when (args.getOrNull(3)) {
                        null -> suggestPlayers()
                        else -> emptyList()
                    }
                }
                else -> emptyList()
            }
            "craft" -> when (args.getOrNull(1)?.lowercase(Locale.getDefault())) {
                null -> suggestions("fastcraft", "grid", "default")
                else -> when (args.getOrNull(2)) {
                    null -> suggestPlayers()
                    else -> emptyList()
                }
            }
            else -> emptyList()
        }
    }

    fun fcSetEnabled(source: FcCommandSource, enabled: Boolean = false, toggle: Boolean = false) {
        val targetPlayer = source.player

        if (targetPlayer == null) {
            source.sendMustBePlayerMessage()
            return
        }

        if (!source.hasPermission(permissions.FASTCRAFT_COMMAND_SET_ENABLED)) {
            source.sendMissingPermissionMessage(permissions.FASTCRAFT_COMMAND_SET_ENABLED)
            return
        }

        val newEnabled = if (toggle) {
            !fastCraftPreferences.getEnabledOrDefault(targetPlayer.uuid)
        } else {
            enabled
        }

        fastCraftPreferences.setEnabled(targetPlayer.uuid, newEnabled)
        source.sendMessage(fcTextFactory.createLegacy(
            if (newEnabled) {
                Strings.commandSetEnabledTrue(source.locale)
            } else {
                Strings.commandSetEnabledFalse(source.locale)
            }
        ))
    }

    fun fcSetEnabledAdmin(source: FcCommandSource, player: String, enabled: Boolean = false, toggle: Boolean = false) {
        val targetPlayer = fcPlayerProvider
            .getOnlinePlayers()
            .firstOrNull { it.username.equals(player, true) }

        if (targetPlayer != null && targetPlayer == source.player) {
            fcSetEnabled(source, enabled, toggle)
            return
        }

        if (!source.hasPermission(permissions.FASTCRAFT_ADMIN_COMMAND_SET_ENABLED)) {
            source.sendMissingPermissionMessage(permissions.FASTCRAFT_ADMIN_COMMAND_SET_ENABLED)
            return
        }

        if (targetPlayer == null) {
            source.sendPlayerNotFoundMessage(player)
            return
        }

        val newEnabled = if (toggle) {
            !fastCraftPreferences.getEnabledOrDefault(targetPlayer.uuid)
        } else {
            enabled
        }

        fastCraftPreferences.setEnabled(targetPlayer.uuid, newEnabled)
        source.sendMessage(fcTextFactory.createLegacy(
            if (newEnabled) {
                Strings.commandSetEnabledTruePlayer(source.locale, targetPlayer.username)
            } else {
                Strings.commandSetEnabledFalsePlayer(source.locale, targetPlayer.username)
            }
        ))
    }

    fun fcCraft(source: FcCommandSource, type: String) {
        val targetPlayer = source.player

        if (targetPlayer == null) {
            source.sendMustBePlayerMessage()
            return
        }

        val hasFcPerm = source.hasPermission(permissions.FASTCRAFT_COMMAND_CRAFT_FASTCRAFT)
        val hasGridPerm = source.hasPermission(permissions.FASTCRAFT_COMMAND_CRAFT_GRID)
        val prefersFc = fastCraftPreferences.getEnabledOrDefault(targetPlayer.uuid)

        val craftType = when (type) {
            "default" -> when (prefersFc) {
                true -> when {
                    hasFcPerm -> "fastcraft"
                    hasGridPerm -> "grid"
                    else -> "fastcraft"
                }
                false -> when {
                    hasGridPerm -> "grid"
                    hasFcPerm -> "fastcraft"
                    else -> "grid"
                }
            }
            else -> type
        }

        when (craftType) {
            "fastcraft" -> {
                if (!hasFcPerm) {
                    source.sendMissingPermissionMessage(permissions.FASTCRAFT_COMMAND_CRAFT_FASTCRAFT)
                    return
                }

                fastCraftGuiFactory
                    .createFastCraftGui(targetPlayer)
                    .open()
            }
            "grid" -> {
                if (!hasGridPerm) {
                    source.sendMissingPermissionMessage(permissions.FASTCRAFT_COMMAND_CRAFT_GRID)
                    return
                }

                targetPlayer.openCraftingTable()
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun fcCraftAdmin(source: FcCommandSource, type: String, player: String) {
        val targetPlayer = fcPlayerProvider
            .getOnlinePlayers()
            .firstOrNull { it.username.equals(player, true) }

        if (targetPlayer != null && targetPlayer == source.player) {
            fcCraft(source, type)
            return
        }

        if (!source.hasPermission(permissions.FASTCRAFT_ADMIN_COMMAND_CRAFT)) {
            source.sendMissingPermissionMessage(permissions.FASTCRAFT_ADMIN_COMMAND_CRAFT)
            return
        }

        if (targetPlayer == null) {
            source.sendPlayerNotFoundMessage(player)
            return
        }

        val craftType = when (type) {
            "default" -> when (fastCraftPreferences.getEnabledOrDefault(targetPlayer.uuid)) {
                true -> "fastcraft"
                false -> "grid"
            }
            else -> type
        }

        when (craftType) {
            "fastcraft" -> {
                fastCraftGuiFactory
                    .createFastCraftGui(targetPlayer)
                    .open()
            }
            "grid" -> {
                targetPlayer.openCraftingTable()
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun fcReload(source: FcCommandSource) {
        if (!source.hasPermission(permissions.FASTCRAFT_ADMIN_COMMAND_RELOAD)) {
            source.sendMissingPermissionMessage(permissions.FASTCRAFT_ADMIN_COMMAND_RELOAD)
            return
        }

        fastCraftConfig.load()
        source.sendMessage(fcTextFactory.create(Strings.commandReloadReloaded(source.locale)))
    }
}
