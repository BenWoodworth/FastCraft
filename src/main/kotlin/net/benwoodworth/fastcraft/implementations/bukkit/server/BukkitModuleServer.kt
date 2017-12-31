package net.benwoodworth.fastcraft.implementations.bukkit.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.Task
import javax.inject.Singleton
import org.bukkit.plugin.Plugin as Bukkit_Plugin

/**
 * Bukkit implementation of [ModuleServer].
 */
@Module
class BukkitModuleServer(
        private val plugin: Bukkit_Plugin
) : ModuleServer {

    @Provides
    override fun taskBuilder(): Task.Builder {
        return BukkitTask.Builder(plugin)
    }

    @Provides
    @Singleton
    override fun pluginProvider(): Plugin.Provider {
        return BukkitPlugin.Provider()
    }
}
