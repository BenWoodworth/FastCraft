package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommand
import net.benwoodworth.fastcraft.platform.server.FcLogger
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFcCommandRegistry_1_7 @Inject constructor(
    private val plugin: Plugin,
    private val server: Server,
    private val fcLogger: FcLogger,
    private val fcCommandAdapterFactory: BukkitFcCommandAdapter.Factory,
) : BukkitFcCommandRegistry {
    override fun register(command: FcCommand, name: String, vararg aliases: String) {
        val pluginCommand = server.getPluginCommand("${plugin.name}:$name")

        if (pluginCommand == null) {
            fcLogger.error("Command not registered in plugin.yml: $name")
        }

        val wrapper = fcCommandAdapterFactory.create(command)

        pluginCommand.aliases = aliases.toList()
        pluginCommand.executor = wrapper
        pluginCommand.tabCompleter = wrapper
        pluginCommand.description = command.description
        pluginCommand.usage = command.usage
    }
}
