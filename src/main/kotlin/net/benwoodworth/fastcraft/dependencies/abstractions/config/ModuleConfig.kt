package net.benwoodworth.fastcraft.dependencies.abstractions.config

/**
 * Dagger module for config dependencies.
 */
interface ModuleConfig {

    fun configManager(): ConfigManager
}
