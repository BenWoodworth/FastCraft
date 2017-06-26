package net.benwoodworth.fastcraft.core.dependencies.server

import java.nio.file.Path

/**
 * A server plugin.
 */
interface Plugin {

    /** This plugin's [Logger]. */
    val logger: Logger

    /** The directory in which this plugin's files are stored. */
    val pluginDirectory: Path

    /** The FastCraft configuration file. */
    val configFile: Path
}
