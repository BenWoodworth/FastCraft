package net.benwoodworth.fastcraft.platform.api.config

import java.nio.file.Path

/**
 * A configuration.
 */
interface FcConfig : FcConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>

    /**
     * Save a config to a [Path].
     *
     * @param path the location to save the [FcConfig] to.
     * @param config the [FcConfig] to save.
     */
    fun saveConfig(config: FcConfig, path: Path)
}
