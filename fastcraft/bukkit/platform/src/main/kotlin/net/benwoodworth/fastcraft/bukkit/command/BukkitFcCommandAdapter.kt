package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommand
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

interface BukkitFcCommandAdapter : CommandExecutor, TabCompleter {
    val command: FcCommand

    interface Factory {
        fun create(command: FcCommand): BukkitFcCommandAdapter
    }
}
