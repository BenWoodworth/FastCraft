package net.benwoodworth.fastcraft.api

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.data.PlayerSettings
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraftPreferencesImpl @Inject constructor(
    private val playerSettings: PlayerSettings,
    private val config: FastCraftConfig,
) : FastCraftPreferences {
    override val defaults: FastCraftPreferences.Defaults =
        object : FastCraftPreferences.Defaults {
            override val enabled: Boolean
                get() = config.playerDefaults.enabled
        }

    override fun getEnabled(playerId: UUID): Boolean? {
        return playerSettings.getFastCraftEnabledOrNull(playerId)
    }

    override fun setEnabled(playerId: UUID, enabled: Boolean?) {
        playerSettings.setFastCraftEnabled(playerId, enabled)
    }

    override fun getEnabledOrDefault(playerId: UUID): Boolean {
        return getEnabled(playerId) ?: defaults.enabled
    }
}
