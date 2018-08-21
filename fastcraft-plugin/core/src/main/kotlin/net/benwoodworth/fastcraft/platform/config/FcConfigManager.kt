package net.benwoodworth.fastcraft.platform.config

import java.nio.file.Path

/**
 * Handles [FcConfig] operations, like loading and saving.
 */
interface FcConfigManager {

    /**
     * Create a new empty [FcConfig].
     *
     * @return a new empty [FcConfig].
     */
    fun getEmptyConfig(): FcConfig

    /**
     * Load a config from a [Path].
     *
     * @param path the location of the [FcConfig] to load.
     * @return the loaded [FcConfig].
     */
    fun loadConfig(path: Path): FcConfig

    /**
     * Save a config to a [Path].
     *
     * @param path the location to save the [FcConfig] to.
     * @param config the [FcConfig] to save.
     */
    fun saveConfig(config: FcConfig, path: Path)

    /**
     * Get the file extension for this config
     * format, including the period.
     *
     * @return the file extension.
     */
    fun getFileExtension(): String
}
