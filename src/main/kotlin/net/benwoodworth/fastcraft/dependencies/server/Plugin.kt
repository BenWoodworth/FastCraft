package net.benwoodworth.fastcraft.dependencies.server

import java.nio.file.Path

/**
 * A server plugin.
 */
interface Plugin {

    /**
     * This plugin's [Logger].
     */
    val logger: Logger

    /**
     * The directory in which this plugin's files are stored.
     */
    val pluginDirectory: Path

    /**
     * The plugin's configuration file.
     */
    val configFileName: String

    /**
     * Provides plugins.
     */
    interface Provider {

        /**
         * Get the plugin with the given name.
         *
         * @return the plugin, or null if it does not exist.
         */
        fun getPlugin(name: String): Plugin?
    }
}
