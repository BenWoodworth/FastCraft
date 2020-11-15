package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import org.bukkit.command.CommandSender

interface BukkitFcCommandSource : FcCommandSource {
    val commandSender: CommandSender

    interface Factory {
        fun create(commandSender: CommandSender): FcCommandSource
    }
}

val FcCommandSource.commandSender: CommandSender
    get() = (this as BukkitFcCommandSource).commandSender
