package net.benwoodworth.fastcraft.core.dependencies.config

/**
 * A configuration.
 */
interface Config : ConfigSection {

    /** The config header comment. */
    var header: String?
}
