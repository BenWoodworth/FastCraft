package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

/**
 * A configuration.
 */
interface FcConfig : Extensible {

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
