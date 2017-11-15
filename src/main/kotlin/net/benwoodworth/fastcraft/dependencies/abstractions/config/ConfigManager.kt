package net.benwoodworth.fastcraft.dependencies.abstractions.config

import java.nio.file.Path

/**
 * Handles [Config] operations, like loading and saving.
 */
interface ConfigManager {

    /**
     * Create a new empty [Config].
     *
     * @return a new empty [Config]
     */
    fun getEmptyConfig(): Config

    /**
     * Load a config from a [Path].
     *
     * @param path the location of the [Config] to load
     * @return the loaded [Config]
     */
    fun loadConfig(path: Path): Config

    /**
     * Save a config to a [Path].
     *
     * @param path the location to save the [Config] to
     * @param config the [Config] to save.
     */
    fun saveConfig(config: Config, path: Path)

    /**
     * Get the file extension for this config
     * format, including the period.
     *
     * @return the file extension
     */
    fun getFileExtension(): String
}
