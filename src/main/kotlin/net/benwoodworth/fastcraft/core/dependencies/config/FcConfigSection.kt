package net.benwoodworth.fastcraft.core.dependencies.config

/**
 * A section of a configuration, providing access to keys/values, and other sub-sections.
 */
interface FcConfigSection {

    /**
     * Get a config section.
     *
     * @param name the section name
     * @return the section with the given name, or `null` if it doesn't exist
     */
    fun getSection(name: String): FcConfigSection?

    /**
     * Create a new config section.
     *
     * @param name the name of the new section
     * @return the new config section
     */
    fun createSection(name: String): FcConfigSection

    /**
     * Set a value at a given key.
     *
     * @param key   the key
     * @param value the value to set
     * @param T   the value type
     */
    fun <T> setValue(key: String, value: T?)

    /**
     * Get a value, given a key.
     *
     * @param key the key
     * @param T the value type
     * @return the value at the given key, or null if the key does not exist
     */
    fun <T> getValue(key: String): T?
}
