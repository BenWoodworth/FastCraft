package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import org.bukkit.command.CommandSender

interface BukkitFcCommandSource : FcCommandSource {
    val commandSender: CommandSender

    companion object {
        val FcCommandSource.commandSender: CommandSender
            get() = (this as BukkitFcCommandSource).commandSender
    }

    interface Factory {
        fun create(commandSender: CommandSender): FcCommandSource
    }
}
