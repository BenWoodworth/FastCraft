package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.Config

/**
 * An interface for objects containing a config.
 */
interface ConfigWrapper {

    /**
     * The wrapped config.
     */
    var config: Config
}
