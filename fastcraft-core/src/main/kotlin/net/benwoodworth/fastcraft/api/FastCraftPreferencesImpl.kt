package net.benwoodworth.fastcraft.api

import net.benwoodworth.fastcraft.data.PlayerSettings
import java.util.*

class FastCraftPreferencesImpl(
    private val playerId: UUID,
    private val playerSettings: PlayerSettings,
) : FastCraftPreferences {
    override var enabled: Boolean
        get() = playerSettings.getFastCraftEnabled(playerId)
        set(value) {
            playerSettings.setFastCraftEnabled(playerId, value)
        }
}
