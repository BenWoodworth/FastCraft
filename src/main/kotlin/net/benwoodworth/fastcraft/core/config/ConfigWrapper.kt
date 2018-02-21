package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.config.FcConfig

/**
 * An interface for objects containing a config.
 */
interface ConfigWrapper {

    /**
     * The wrapped config.
     */
    var config: FcConfig
}
