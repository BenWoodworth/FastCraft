package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.data.PlayerSettings
import net.benwoodworth.fastcraft.platform.command.FcCommand
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraftCommand @Inject constructor(
    private val commandRegistry: FcCommandRegistry,
    private val textFactory: FcTextFactory,
    private val playerProvider: FcPlayer.Provider,
    private val playerSettings: PlayerSettings,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
    private val permissions: Permissions,
) : FcCommand {
    override val description = "FastCraft command"

    override val usage = "/fastcraft [set|craft] ..."
    private val usageSet = "/fastcraft set (enabled) <option> [player]"
    private val usageSetEnabled = "/fastcraft set enabled (true|false) [player]"
    private val usageCraft = "/fastcraft craft [fastcraft|grid|default] [player]"

    private fun FcCommandSource.sendMissingPermissionMessage(permission: FcPermission) {
        sendMessage(textFactory.createFcText(
            text = Strings.commandErrorPermission(permission),
        ))
    }

    private fun FcCommandSource.sendMustBePlayerMessage() {
        sendMessage(textFactory.createFcText(
            text = Strings.commandErrorPlayerOnly(),
        ))
    }

    private fun FcCommandSource.sendPlayerNotFoundMessage(player: String) {
        sendMessage(textFactory.createFcText(
            text = Strings.commandErrorPlayerUnknown(player),
        ))
    }

    private fun FcCommandSource.sendUsageMessage(usage: String) {
        sendMessage(textFactory.createFcText(
            text = Strings.commandErrorUsage(usage),
        ))
    }

    private val argSplitExpr = Regex("""\s+""")

    fun register() {
        commandRegistry.register(this, "fastcraft", "fc")
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
            val players = playerProvider
                .getOnlinePlayers()
                .map { it.username }
                .toTypedArray()

            return suggestions(*players)
        }

        return when (args.getOrNull(0)?.toLowerCase()) {
            null -> suggestions("set", "craft")
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
        source.sendMessage(textFactory.createLegacy(
            if (enabled) {
                Strings.commandSetEnabledTrue()
            } else {
                Strings.commandSetEnabledFalse()
            }
        ))
    }

    fun fcSetEnabledAdmin(source: FcCommandSource, enabled: Boolean, player: String) {
        val targetPlayer = playerProvider
            .getOnlinePlayers()
            .firstOrNull { it.username.equals(player, true) }

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
        source.sendMessage(textFactory.createLegacy(
            if (enabled) {
                Strings.commandSetEnabledTruePlayer(targetPlayer.username)
            } else {
                Strings.commandSetEnabledFalsePlayer(targetPlayer.username)
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

                targetPlayer.openCraftingTable()
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun fcCraftAdmin(source: FcCommandSource, type: String, player: String) {
        val targetPlayer = playerProvider
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
                targetPlayer.openCraftingTable()
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}
