package net.benwoodworth.fastcraft.core.dependencies.config

import java.io.File
import java.io.IOException

/**
 * A configuration.
 */
interface Config {

    /** The config header comment. */
    val header: String?

    /**
     * Load a config from a file.
     *
     * @param file the [File] to load
     * @throws IOException if there was a problem loading the config
     */
    fun load(file: File)

    /**
     * Save this [Config] to a file.
     *
     * @param file the [File] to save to
     * @throws IOException if there was a problem saving the config
     */
    fun save(file: File)
}
