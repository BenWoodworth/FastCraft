package net.benwoodworth.fastcraft.dependencies.abstractions.config

/**
 * A section of a configuration, providing access to keys/values, and other sub-sections.
 */
interface ConfigSection {

    /**
     * See if this [ConfigSection] has a key.
     *
     * @param key the key to check
     * @return true iff the key exists
     */
    fun hasKey(key: String): Boolean

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
     * Remove the section at the given key.
     *
     * @param key the key to remove
     */
    fun removeSection(key: String)

    /**
     * Remove the value at the given key.
     *
     * @param key the key to remove
     */
    fun removeValue(key: String)

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
    fun setString(key: String, value: String?)

    /**
     * Get a [List] of [String]s at the given key.
     *
     * @param key the key
     * @return the value at the given key, or null if the key does not exist
     */
    fun getStringList(key: String): List<String>?

    /**
     * Set a [List] of [String]s at a given key.
     *
     * @param key the key
     * @param value the value to set
     */
    fun setStringList(key: String, value: List<String>?)

    /**
     * Get an [Int] at the given key.
     *
     * @param key the key
     * @return the value at the given key, or null if the key does not exist
     */
    fun getInt(key: String): Int?

    /**
     * Set an [Int] at a given key.
     *
     * @param key the key
     * @param value the value to set
     */
    fun setInt(key: String, value: Int?)

    /**
     * Get a [List] of [Int]s at the given key.
     *
     * @param key the key
     * @return the value at the given key, or null if the key does not exist
     */
    fun getIntList(key: String): List<Int>?

    /**
     * Set a [List] of [Int]s at a given key.
     *
     * @param key the key
     * @param value the value to set
     */
    fun setIntList(key: String, value: List<Int>?)

    /**
     * Get a [Boolean] at the given key.
     *
     * @param key the key
     * @return the value at the given key, or null if the key does not exist
     */
    fun getBoolean(key: String): Boolean?

    /**
     * Set a [Boolean] at a given key.
     *
     * @param key the key
     * @param value the value to set
     */
    fun setBoolean(key: String, value: Boolean?)
}
