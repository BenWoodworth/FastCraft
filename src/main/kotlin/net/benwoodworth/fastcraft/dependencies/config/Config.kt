package net.benwoodworth.fastcraft.dependencies.config

/**
 * A configuration.
 */
interface Config : ConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>
}
