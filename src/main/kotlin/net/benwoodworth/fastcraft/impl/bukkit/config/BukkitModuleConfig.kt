package net.benwoodworth.fastcraft.impl.bukkit.config

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.config.ConfigManager
import net.benwoodworth.fastcraft.dependencies.config.ModuleConfig
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
