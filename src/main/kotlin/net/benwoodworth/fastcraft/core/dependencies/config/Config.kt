package net.benwoodworth.fastcraft.core.dependencies.config

import java.io.IOException
import java.nio.file.Path

/**
 * A configuration.
 */
interface Config : ConfigSection {

    /** The config header comment. */
    var header: String?

    /**
     * Load a config from a file.
     *
     * @param path the [Path] to load
     * @throws IOException if there was a problem loading the config
     */
    fun load(path: Path)

    /**
     * Save this [Config] to a file.
     *
     * @param path the [Path] to save to
     * @throws IOException if there was a problem saving the config
     */
    fun save(path: Path)
}
