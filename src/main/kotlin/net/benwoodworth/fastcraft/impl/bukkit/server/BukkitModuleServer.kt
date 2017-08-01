package net.benwoodworth.fastcraft.impl.bukkit.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.Task
import javax.inject.Singleton

/**
 * Bukkit implementation of [ModuleServer].
 */
@Module
class BukkitModuleServer : ModuleServer {

    @Provides
    override fun taskBuilder(): Task.Builder {
        TODO("not implemented")
    }

    @Provides @Singleton
    override fun pluginProvider(): Plugin.Provider {
        TODO("not implemented")
    }
}
