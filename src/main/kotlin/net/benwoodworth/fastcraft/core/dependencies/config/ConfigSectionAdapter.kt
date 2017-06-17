package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.Adapter

/**
 * A section of a configuration, providing access to keys/values, and other sub-sections.
 */
abstract class ConfigSectionAdapter(baseConfig: Any) : Adapter(baseConfig) {

    /**
     * Get a config section.
     *
     * @param name the section name
     * @return the section with the given name, or `null` if it doesn't exist
     */
    abstract fun getSection(name: String): ConfigSectionAdapter?

    /**
     * Create a new config section.
     *
     * @param name the name of the new section
     * @return the new config section
     */
    abstract fun createSection(name: String): ConfigSectionAdapter

    /**
     * Set a value at a given key.
     *
     * @param key   the key
     * @param value the value to set
     * @param T   the value type
     */
    abstract fun <T> setValue(key: String, value: T?)

    /**
     * Get a value, given a key.
     *
     * @param key the key
     * @param T the value type
     * @return the value at the given key, or null if the key does not exist
     */
    abstract fun <T> getValue(key: String): T?
}
