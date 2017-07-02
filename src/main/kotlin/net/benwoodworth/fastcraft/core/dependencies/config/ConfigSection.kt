package net.benwoodworth.fastcraft.core.dependencies.config

/**
 * A section of a configuration, providing access to keys/values, and other sub-sections.
 */
interface ConfigSection {

    /**
     * Get a config section, or create a new one if it does not exist.
     *
     * @param name the section name
     * @return the section with the given name
     */
    fun getSection(name: String): ConfigSection

    /**
     * Set a value at a given key.
     *
     * @param key the key
     * @param value the value to set
     * @param T the value type
     */
    fun <T> set(key: String, value: T?)

    /**
     * Get a value, given a key.
     *
     * @param key the key
     * @param T the value type
     * @return the value at the given key, or null if the key does not exist
     */
    fun <T> get(key: String): T?
}
