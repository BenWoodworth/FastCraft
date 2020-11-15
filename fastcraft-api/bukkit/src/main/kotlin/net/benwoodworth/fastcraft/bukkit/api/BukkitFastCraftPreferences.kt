package net.benwoodworth.fastcraft.bukkit.api

import net.benwoodworth.fastcraft.api.FastCraftPreferences
import org.bukkit.OfflinePlayer

/**
 * Access to get/set players' FastCraft preferences.
 * @since 3.2.9
 */
interface BukkitFastCraftPreferences : FastCraftPreferences {
    /**
     * @since 3.2.9
     */
    fun getEnabled(player: OfflinePlayer): Boolean?

    /**
     * @since 3.2.9
     */
    fun setEnabled(player: OfflinePlayer, enabled: Boolean?)

    /**
     * @since 3.2.9
     */
    fun getEnabledOrDefault(player: OfflinePlayer): Boolean
}
