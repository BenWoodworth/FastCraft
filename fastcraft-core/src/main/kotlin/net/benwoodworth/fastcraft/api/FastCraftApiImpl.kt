package net.benwoodworth.fastcraft.api

import net.benwoodworth.fastcraft.data.PlayerSettings
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraftApiImpl @Inject constructor(
    private val playerSettings: PlayerSettings,
) : FastCraftApi {
    override fun getPreferences(playerId: UUID): FastCraftPreferences {
        return FastCraftPreferencesImpl(playerId, playerSettings)
    }
}
