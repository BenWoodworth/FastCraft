package net.benwoodworth.fastcraft.bukkit.api

import net.benwoodworth.fastcraft.api.FastCraftApi

/**
 * @since 3.2.9
 */
interface BukkitFastCraftApi : FastCraftApi {
    companion object {
        /**
         * Should only be accessed after FastCraft is enabled.
         * @since 3.2.9
         */
        val api: BukkitFastCraftApi
            @JvmStatic get() = FastCraftApi.api as BukkitFastCraftApi
    }

    /**
     * @see FastCraftApi.preferences
     * @since 3.2.9
     */
    override val preferences: BukkitFastCraftPreferences
}
