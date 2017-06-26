package net.benwoodworth.fastcraft.core.dependencies

import java.nio.file.Path

/**
 * Provides files for the server.
 */
interface ServerFileProvider {

    /** The directory in which this plugin's files are stored */
    val pluginDirectory: Path

    /** The FastCraft configuration file */
    val configFile: Path
}
