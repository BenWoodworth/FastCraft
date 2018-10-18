package net.benwoodworth.fastcraft.platform.api.config

import java.nio.file.Path

/**
 * A configuration.
 */
interface FcConfig {

    /**
     * The config header comment.
     */
    var header: String?

    /**
     * Save a config to a [Path].
     */
    fun save(path: Path)

    operator fun get(key: String): FcConfigEntry
}
