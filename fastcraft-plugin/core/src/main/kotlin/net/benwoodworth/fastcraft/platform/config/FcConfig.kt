package net.benwoodworth.fastcraft.platform.config

/**
 * A configuration.
 */
interface FcConfig : FcConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>
}
