package net.benwoodworth.fastcraft.platform.api.config

/**
 * A configuration.
 */
interface FcConfig : FcConfigSection {

    /**
     * The config header comment.
     */
    var header: List<String>
}
