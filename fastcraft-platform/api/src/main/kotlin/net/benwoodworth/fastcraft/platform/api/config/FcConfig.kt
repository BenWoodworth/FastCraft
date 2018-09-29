package net.benwoodworth.fastcraft.platform.api.config

import java.nio.file.Path

/**
 * A configuration.
 */
interface FcConfig : FcConfigSection {

    /**
     * The config header comment.
     */
    var header: String

    /**
     * Save a config to a [Path].
     */
    fun save(path: Path)
}
