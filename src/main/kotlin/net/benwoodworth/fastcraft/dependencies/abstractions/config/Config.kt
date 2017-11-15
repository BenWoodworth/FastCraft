package net.benwoodworth.fastcraft.dependencies.abstractions.config

/**
 * A configuration.
 */
interface Config : ConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>
}
