package net.benwoodworth.fastcraft.commands

import net.benwoodworth.fastcraft.Permissions
import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.data.PlayerSettings
import net.benwoodworth.fastcraft.platform.command.FcCommand
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraftCommand @Inject constructor(
    private val commandRegistry: FcCommandRegistry,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val playerProvider: FcPlayer.Provider,
    private val playerPrefs: PlayerSettings,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
) : FcCommand {
    override val usage = textFactory.createFcText(
        text = "/fastcraft [set|craft|help] ...",
        color = textColors.red,
    )

    private val usageSet = textFactory.createFcText(
        text = "/fastcraft set (enabled) <option> [player]",
        color = textColors.red,
    )

    private val usageSetEnabled = textFactory.createFcText(
        text = "/fastcraft set enabled (true|false) [player]",
        color = textColors.red,
    )

    private val usageCraft = textFactory.createFcText(
        text = "/fastcraft craft [fastcraft|grid|default] [player]",
        color = textColors.red,
    )

    private fun FcCommandSource.sendMissingPermissionMessage(permission: String) {
        sendMessage(textFactory.createFcText(
            text = "Missing permission: $permission", // TODO Localize
            color = textColors.red,
        ))
    }

    private fun FcCommandSource.sendMustBePlayerMessage() {
        sendMessage(textFactory.createFcText(
            text = "Must be a player to use this command", // TODO Localize
            color = textColors.red,
        ))
    }

    private fun FcCommandSource.sendPlayerNotFoundMessage(player: String) {
        sendMessage(textFactory.createFcText(
            text = "Player not found: $player", // TODO Localize
            color = textColors.red,
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
                            else -> source.sendMessage(usageSetEnabled)
                        }
                    }
                    else -> {
                        source.sendMessage(usageSetEnabled)
                    }
                }
                else -> {
                    source.sendMessage(usageSet)
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
                            source.sendMessage(usageCraft)
                        }
                    }
                }
                else -> {
                    source.sendMessage(usageCraft)
                }
            }
            else -> {
                source.sendMessage(usage)
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
            null -> suggestions("help", "set", "craft")
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

        if (!source.hasPermission(Permissions.FASTCRAFT_COMMAND_SET_ENABLED)) {
            source.sendMissingPermissionMessage(Permissions.FASTCRAFT_COMMAND_SET_ENABLED)
            return
        }

        playerPrefs.setFastCraftEnabled(targetPlayer, enabled)
        source.sendMessage(textFactory.createFcText(
            text = "Enabled = $enabled", // TODO localize
            color = textColors.green,
        ))
    }

    fun fcSetEnabledAdmin(source: FcCommandSource, enabled: Boolean, player: String) {
        if (!source.hasPermission(Permissions.FASTCRAFT_ADMIN_COMMAND_SET_ENABLED)) {
            source.sendMissingPermissionMessage(Permissions.FASTCRAFT_ADMIN_COMMAND_SET_ENABLED)
            return
        }

        val targetPlayer = playerProvider
            .getOnlinePlayers()
            .firstOrNull { it.username.equals(player, true) }

        if (targetPlayer == null) {
            source.sendPlayerNotFoundMessage(player)
            return
        }

        playerPrefs.setFastCraftEnabled(targetPlayer, enabled)
        source.sendMessage(textFactory.createFcText(
            text = "${targetPlayer.username}: Enabled = $enabled", // TODO localize
            color = textColors.green,
        ))
    }

    fun fcCraft(source: FcCommandSource, type: String) {
        val targetPlayer = source.player

        if (targetPlayer == null) {
            source.sendMustBePlayerMessage()
            return
        }

        val hasFcPerm = source.hasPermission(Permissions.FASTCRAFT_COMMAND_CRAFT_FASTCRAFT)
        val hasGridPerm = source.hasPermission(Permissions.FASTCRAFT_COMMAND_CRAFT_GRID)
        val prefersFc = playerPrefs.getFastCraftEnabled(targetPlayer)

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
                    source.sendMissingPermissionMessage(Permissions.FASTCRAFT_COMMAND_CRAFT_FASTCRAFT)
                    return
                }

                fastCraftGuiFactory
                    .createFastCraftGui(targetPlayer)
                    .open()
            }
            "grid" -> {
                if (!hasGridPerm) {
                    source.sendMissingPermissionMessage(Permissions.FASTCRAFT_COMMAND_CRAFT_GRID)
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
        if (!source.hasPermission(Permissions.FASTCRAFT_ADMIN_COMMAND_CRAFT)) {
            source.sendMissingPermissionMessage(Permissions.FASTCRAFT_ADMIN_COMMAND_CRAFT)
            return
        }

        val targetPlayer = playerProvider
            .getOnlinePlayers()
            .firstOrNull { it.username.equals(player, true) }

        if (targetPlayer == null) {
            source.sendPlayerNotFoundMessage(player)
            return
        }

        val craftType = when (type) {
            "default" -> when (playerPrefs.getFastCraftEnabled(targetPlayer)) {
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
