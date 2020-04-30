package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer.Provider.Companion.getPlayer
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject

class BukkitFcCommandSource_1_7(
    override val commandSender: CommandSender,
    override val player: FcPlayer?,
    private val textConverter: FcTextConverter,
) : BukkitFcCommandSource {
    override val isConsole: Boolean
        get() = commandSender is ConsoleCommandSender

    override fun hasPermission(permission: String): Boolean {
        return commandSender.hasPermission(permission)
    }

    override fun sendMessage(message: FcText) {
        if (player != null) {
            player.sendMessage(message)
        } else {
            commandSender.sendMessage(textConverter.toLegacy(message, Locale.ENGLISH))
        }
    }

    class Factory @Inject constructor(
        private val playerProvider: FcPlayer.Provider,
        private val textConverter: FcTextConverter,
    ) : BukkitFcCommandSource.Factory {
        override fun create(commandSender: CommandSender): FcCommandSource {
            return BukkitFcCommandSource_1_7(
                commandSender = commandSender,
                player = when (commandSender) {
                    is Player -> playerProvider.getPlayer(commandSender)
                    else -> null
                },
                textConverter = textConverter
            )
        }
    }
}
