package net.benwoodworth.fastcraft.commands

import net.benwoodworth.fastcraft.Permissions
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
) : FcCommand {
    override val permission: String?
        get() = Permissions.FASTCRAFT_COMMAND

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
        text = "/fastcraft craft [fastcraft|grid|-] [player]",
        color = textColors.red,
    )

    private fun FcPlayer.sendPermissionMessage(permission: String) {
        sendMessage(textFactory.createFcText(
            text = "Missing permission: $permission",
            color = textColors.red,
        ))
    }

    private val argSplitExpr = Regex("""\s+""")

    fun register() {
        commandRegistry.register(this, "fastcraft", "fc")
    }

    override fun process(source: FcCommandSource, arguments: String) {
        val args = arguments.split(argSplitExpr)

        val x: Unit = when (args.getOrNull(0)?.toLowerCase()) {
            "set" -> when (args.getOrNull(1)?.toLowerCase()) {
                "enabled" -> when (val enabled = args.getOrNull(2)?.toLowerCase()) {
                    "true",
                    "false",
                    -> when (val player = args.getOrNull(3)) {
                        null -> {
                            fcSetEnabled(source, enabled.toBoolean(), player)
                        }
                        else -> when (args.getOrNull(4)) {
                            null -> fcSetEnabled(source, enabled.toBoolean(), player)
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
            "craft" -> when (val type = args.getOrNull(1)?.toLowerCase() ?: "preferred") {
                "fastcraft",
                "grid",
                "preferred",
                -> when (val player = args.getOrNull(2)) {
                    null -> {
                        fcCraft(source, type, player)
                    }
                    else -> when (args.getOrNull(3)) {
                        null -> {
                            fcCraft(source, type, player)
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
                null -> suggestions("fastcraft", "grid", "preferred")
                else -> when (args.getOrNull(2)) {
                    null -> suggestPlayers()
                    else -> emptyList()
                }
            }
            else -> emptyList()
        }
    }

    fun fcSetEnabled(source: FcCommandSource, enabled: Boolean, player: String?) {
        println("fcSetEnabled $enabled $player")
    }

    fun fcCraft(source: FcCommandSource, type: String, player: String?) {
        println("fcCraft $type $player")
    }
}
