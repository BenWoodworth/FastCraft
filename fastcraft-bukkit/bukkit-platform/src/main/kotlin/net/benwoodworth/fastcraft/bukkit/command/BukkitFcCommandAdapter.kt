package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommand
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

interface FcCommandAdapter_Bukkit : CommandExecutor, TabCompleter {
    val command: FcCommand

    interface Factory {
        fun create(command: FcCommand): FcCommandAdapter_Bukkit
    }
}
