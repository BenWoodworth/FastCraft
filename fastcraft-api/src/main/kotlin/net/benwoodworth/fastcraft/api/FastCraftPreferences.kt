package net.benwoodworth.fastcraft.api

import java.util.*

/**
 * Access to get/set players' FastCraft preferences.
 * @since 3.2.9
 */
interface FastCraftPreferences {
    /**
     * The default values for player preferences.
     * @since 3.2.9
     */
    val defaults: Defaults

    interface Defaults {
        /**
         * @since 3.2.9
         */
        val enabled: Boolean
    }

    /**
     * @since 3.2.9
     */
    fun getEnabled(playerId: UUID): Boolean?

    /**
     *
     * @since 3.2.9
     */
    fun setEnabled(playerId: UUID, enabled: Boolean?)

    /**
     * @since 3.2.9
     */
    fun getEnabledOrDefault(playerId: UUID): Boolean
}
