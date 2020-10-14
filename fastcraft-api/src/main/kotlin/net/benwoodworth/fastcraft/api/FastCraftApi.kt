package net.benwoodworth.fastcraft.api

/**
 * @since 3.2.9
 */
interface FastCraftApi {
    companion object {
        /**
         * Should only be accessed after FastCraft is enabled.
         * @since 3.2.9
         */
        lateinit var api: FastCraftApi
            @JvmStatic get
            @Deprecated("Should only be set by FastCraft") set
    }

    /**
     * Access to get/set Players' FastCraft preferences.
     * @since 3.2.9
     */
    val preferences: FastCraftPreferences
}
