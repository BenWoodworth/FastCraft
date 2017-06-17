package net.benwoodworth.fastcraft.core.dependencies.config

import java.io.File
import java.io.IOException

/**
 * A configuration.
 */
abstract class ConfigAdapter(baseConfig: Any) : ConfigSectionAdapter(baseConfig) {

    /** The config header comment. */
    abstract val header: String?

    /**
     * Load a config from a file.
     *
     * @param file the [File] to load
     * @throws IOException if there was a problem loading the config
     */
    abstract fun load(file: File)

    /**
     * Save this [ConfigAdapter] to a file.
     *
     * @param file the [File] to save to
     * @throws IOException if there was a problem saving the config
     */
    abstract fun save(file: File)
}
