package net.benwoodworth.fastcraft.platform.server

import java.nio.file.Path

/**
 * A server plugin.
 */
interface FcPlugin {

    /**
     * This plugin's [FcLogger].
     */
    val logger: FcLogger

    /**
     * The directory in which this plugin's files are stored.
     */
    val pluginDirectory: Path

    /**
     * The plugin's configuration file.
     */
    val configFileName: String
}
