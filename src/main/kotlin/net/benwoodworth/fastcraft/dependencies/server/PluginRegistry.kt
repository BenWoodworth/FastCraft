package net.benwoodworth.fastcraft.dependencies.server

/**
 * Provides plugins.
 */
interface PluginRegistry {

    /**
     * Get the plugin with the given name.
     *
     * @return the plugin, or null if it does not exist.
     */
    fun getPlugin(name: String): Plugin?
}