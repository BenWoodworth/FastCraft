package net.benwoodworth.fastcraft.dependencies.config

/**
 * A configuration.
 */
interface FcConfig : FcConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>
}
