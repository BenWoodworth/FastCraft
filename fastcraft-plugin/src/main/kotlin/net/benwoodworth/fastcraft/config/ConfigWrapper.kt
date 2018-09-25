package net.benwoodworth.fastcraft.config

import net.benwoodworth.fastcraft.platform.api.config.FcConfig

/**
 * An interface for objects containing a config.
 */
interface ConfigWrapper {

    /**
     * The wrapped config.
     */
    var config: FcConfig
}
