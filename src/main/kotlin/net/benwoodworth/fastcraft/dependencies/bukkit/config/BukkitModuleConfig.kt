package net.benwoodworth.fastcraft.dependencies.bukkit.config

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigManager
import net.benwoodworth.fastcraft.dependencies.abstractions.config.ModuleConfig
import javax.inject.Singleton

/**
 * Bukkit implementation of [ModuleConfig].
 */
@Module
class BukkitModuleConfig : ModuleConfig {

    @Provides @Singleton
    override fun configManager(): ConfigManager {
        return BukkitConfigManager()
    }
}
