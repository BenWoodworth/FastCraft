package net.benwoodworth.fastcraft.core.dependencies.config

/**
 * A section of a configuration, providing access to keys/values, and other sub-sections.
 */
interface ConfigSection {

    /**
     * Get a config section at the given key, or create a new one if it does not exist.
     *
     * @param key the section key
     * @return the config section at the given key
     */
    fun getSection(key: String): ConfigSection

    /**
     * Get a config section at the given key, or create a new one if it does not exist.
     *
     * @param key the section key
     * @return the config section at the given key
     */
    operator fun get(key: String) = getSection(key)

    /**
     * Remove a value or config section at the given key.
     *
     * @param key the key to remove
     */
    fun remove(key: String)

    /**
     * Get a [String] at the given key.
     *
     * @param key the key
     * @return the value at the given key, or null if the key does not exist
     */
    fun getString(key: String): String?

    /**
     * Set a [String] at a given key.
     *
     * @param key the key
     * @param value the value to set
     */
    fun setString(key: String, value: String)
}
