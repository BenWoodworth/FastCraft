package net.benwoodworth.fastcraft.bukkit.api

import net.benwoodworth.fastcraft.api.FastCraftPreferences
import net.benwoodworth.fastcraft.api.FastCraftPreferencesImpl
import org.bukkit.OfflinePlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFastCraftPreferencesImpl @Inject constructor(
    fastCraftPreferences: FastCraftPreferencesImpl,
) : BukkitFastCraftPreferences, FastCraftPreferences by fastCraftPreferences {
    override fun getEnabled(player: OfflinePlayer): Boolean? {
        return getEnabled(player.uniqueId)
    }

    override fun setEnabled(player: OfflinePlayer, enabled: Boolean?) {
        setEnabled(player.uniqueId, enabled)
    }

    override fun getEnabledOrDefault(player: OfflinePlayer): Boolean {
        return getEnabledOrDefault(player.uniqueId)
    }
}
