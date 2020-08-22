package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.bukkit.player.getPlayer
import net.benwoodworth.fastcraft.bukkit.server.permission
import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcCommandSource_1_7(
    override val commandSender: CommandSender,
    override val player: FcPlayer?,
    private val fcTextConverter: FcTextConverter,
    private val fcPlayerOperations: FcPlayer.Operations,
) : BukkitFcCommandSource {
    override val isConsole: Boolean
        get() = commandSender is ConsoleCommandSender

    override fun hasPermission(permission: FcPermission): Boolean {
        return commandSender.hasPermission(permission.permission)
    }

    override val locale: Locale
        get() = fcPlayerOperations.run { player?.locale } ?: Locale.ENGLISH // TODO Don't default to English

    override fun sendMessage(message: FcText) {
        if (player != null) {
            fcPlayerOperations.run { player.sendMessage(message) }
        } else {
            commandSender.sendMessage(fcTextConverter.toLegacy(message, Locale.ENGLISH))
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val fcPlayerProvider: FcPlayer.Provider,
        private val fcTextConverter: FcTextConverter,
        private val fcPlayerOperations: FcPlayer.Operations,
    ) : BukkitFcCommandSource.Factory {
        override fun create(commandSender: CommandSender): FcCommandSource {
            return BukkitFcCommandSource_1_7(
                commandSender = commandSender,
                player = when (commandSender) {
                    is Player -> fcPlayerProvider.getPlayer(commandSender)
                    else -> null
                },
                fcTextConverter = fcTextConverter,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
