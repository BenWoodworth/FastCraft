package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.data.PlayerSettings
import net.benwoodworth.fastcraft.platform.command.FcCommand
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import javax.inject.Inject

class FastCraftCommand @Inject constructor(
    private val fcCommandRegistry: FcCommandRegistry,
    private val fcTextFactory: FcText.Factory,
    private val fcPlayerProvider: FcPlayer.Provider,
    private val playerSettings: PlayerSettings,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
    private val permissions: Permissions,
    private val fastCraftConfig: FastCraftConfig,
    private val fcPlayerTypeClass: FcPlayer.TypeClass,
) : FcCommand {
    override val description = "FastCraft command"

    override val usage = "/fastcraft (set|craft|reload) ..."
    private val usageSet = "/fastcraft set (enabled) <option> [<player>]"
    private val usageSetEnabled = "/fastcraft set enabled (true|false) [<player>]"
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

        when (args.getOrNull(0)?.toLowerCase()) {
            "set" -> when (args.getOrNull(1)?.toLowerCase()) {
                "enabled" -> when (val enabled = args.getOrNull(2)?.toLowerCase()) {
                    "true",
                    "false",
                    -> when (val player = args.getOrNull(3)) {
                        null -> {
                            fcSetEnabled(source, enabled.toBoolean())
                        }
                        else -> when (args.getOrNull(4)) {
                            null -> fcSetEnabledAdmin(source, enabled.toBoolean(), player)
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
            "craft" -> when (val type = args.getOrNull(1)?.toLowerCase() ?: "default") {
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
            "reload" -> when (args.getOrNull(1)?.toLowerCase()) {
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
                .filter { it.toLowerCase().startsWith(argInProgress.toLowerCase()) }
                .toList()
        }

        fun suggestPlayers(): List<String> {
            val players = fcPlayerProvider
                .getOnlinePlayers()
                .map { fcPlayerTypeClass.run { it.username } }
                .toTypedArray()

            return suggestions(*players)
        }

        return when (args.getOrNull(0)?.toLowerCase()) {
            null -> suggestions("set", "craft", "reload")
            "set" -> when (args.getOrNull(1)?.toLowerCase()) {
                null -> suggestions("enabled")
                "enabled" -> when (args.getOrNull(2)?.toLowerCase()) {
                    null -> suggestions("true", "false")
                    else -> when (args.getOrNull(3)) {
                        null -> suggestPlayers()
                        else -> emptyList()
                    }
                }
                else -> emptyList()
            }
            "craft" -> when (args.getOrNull(1)?.toLowerCase()) {
                null -> suggestions("fastcraft", "grid", "default")
                else -> when (args.getOrNull(2)) {
                    null -> suggestPlayers()
                    else -> emptyList()
                }
            }
            else -> emptyList()
        }
    }

    fun fcSetEnabled(source: FcCommandSource, enabled: Boolean) {
        val targetPlayer = source.player

        if (targetPlayer == null) {
            source.sendMustBePlayerMessage()
            return
        }

        if (!source.hasPermission(permissions.FASTCRAFT_COMMAND_SET_ENABLED)) {
            source.sendMissingPermissionMessage(permissions.FASTCRAFT_COMMAND_SET_ENABLED)
            return
        }

        playerSettings.setFastCraftEnabled(targetPlayer, enabled)
        source.sendMessage(fcTextFactory.createLegacy(
            if (enabled) {
                Strings.commandSetEnabledTrue(source.locale)
            } else {
                Strings.commandSetEnabledFalse(source.locale)
            }
        ))
    }

    fun fcSetEnabledAdmin(source: FcCommandSource, enabled: Boolean, player: String) {
        val targetPlayer = fcPlayerProvider
            .getOnlinePlayers()
            .firstOrNull { fcPlayerTypeClass.run { it.username }.equals(player, true) }

        if (targetPlayer != null && targetPlayer == source.player) {
            fcSetEnabled(source, enabled)
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

        playerSettings.setFastCraftEnabled(targetPlayer, enabled)
        source.sendMessage(fcTextFactory.createLegacy(
            if (enabled) {
                Strings.commandSetEnabledTruePlayer(source.locale, fcPlayerTypeClass.run { targetPlayer.username })
            } else {
                Strings.commandSetEnabledFalsePlayer(source.locale, fcPlayerTypeClass.run { targetPlayer.username })
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
        val prefersFc = playerSettings.getFastCraftEnabled(targetPlayer)

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

                fcPlayerTypeClass.run { targetPlayer.openCraftingTable() }
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun fcCraftAdmin(source: FcCommandSource, type: String, player: String) {
        val targetPlayer = fcPlayerProvider
            .getOnlinePlayers()
            .firstOrNull { fcPlayerTypeClass.run { it.username }.equals(player, true) }

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
            "default" -> when (playerSettings.getFastCraftEnabled(targetPlayer)) {
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
                fcPlayerTypeClass.run { targetPlayer.openCraftingTable() }
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
