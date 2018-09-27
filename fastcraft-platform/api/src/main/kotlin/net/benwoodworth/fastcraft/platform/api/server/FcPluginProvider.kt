package net.benwoodworth.fastcraft.platform.api.server

/**
 * Provides plugins.
 */
interface FcPluginProvider {

    /**
     * Get the plugin with the given id.
     *
     * @return the plugin, or null if it does not exist.
     */
    fun getPlugin(id: String): FcPlugin?

    /**
     * Get the FastCraft plugin.
     */
    fun getFastCraft(): FcPlugin
}