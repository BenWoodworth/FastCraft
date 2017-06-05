package net.benwoodworth.fastcraft.core.dependencies.config

/**
 * Provides new configs.
 */
interface FcConfigFactory {

    /**
     * Create a new empty config.
     *
     * @return a new empty config
     */
    fun createEmptyConfig(): FcConfig
}
