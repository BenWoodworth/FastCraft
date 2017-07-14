package net.benwoodworth.fastcraft.dependencies.config

/**
 * Dagger module for config dependencies.
 */
interface ModuleConfig {

    fun configService(): ConfigManager
}
