package net.benwoodworth.fastcraft.api

import java.util.*

/**
 * @since 3.2.9
 */
interface FastCraftApi {
    companion object {
        /**
         * Should only be accessed after FastCraft is enabled.
         * @since 3.2.9
         */
        var instance: FastCraftApi? = null
            @JvmStatic get
            @Deprecated("Should only be set by FastCraft") set
    }

    /**
     * Get a player's [FastCraftPreferences], which can be used to get/set preferences.
     * @since 3.2.9
     */
    fun getPreferences(playerId: UUID): FastCraftPreferences
}
