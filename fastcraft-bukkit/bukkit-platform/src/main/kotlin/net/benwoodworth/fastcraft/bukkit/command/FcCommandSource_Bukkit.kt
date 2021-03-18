package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommandSource
import org.bukkit.command.CommandSender

interface FcCommandSource_Bukkit : FcCommandSource {
    val commandSender: CommandSender

    interface Factory {
        fun create(commandSender: CommandSender): FcCommandSource
    }
}

val FcCommandSource.commandSender: CommandSender
    get() = (this as FcCommandSource_Bukkit).commandSender
