package net.benwoodworth.fastcraft.dependencies.bukkit.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.server.ModuleServer
import net.benwoodworth.fastcraft.dependencies.abstractions.server.Plugin
import net.benwoodworth.fastcraft.dependencies.abstractions.server.Task
import org.bukkit.plugin.Plugin as Bukkit_Plugin
import javax.inject.Singleton

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

    @Provides @Singleton
    override fun pluginProvider(): Plugin.Provider {
        return BukkitPlugin.Provider()
    }
}
