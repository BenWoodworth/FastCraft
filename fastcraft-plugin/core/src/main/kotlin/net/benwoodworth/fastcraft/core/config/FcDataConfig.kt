package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.config.FcConfig

/**
 * Manages the data config.
 */
class FcDataConfig(
        override var config: FcConfig
) : ConfigWrapper {

    /**
     * The config revision, which increments whenever changes are made.
     */
    var revision: Int?
        get() = config.getInt("config-revision")
        set(value) = config.setInt("config-revision", value)

    /**
     * Get a player's preferences.
     *
     * @param player the player.
     * @return the player's preferences.
     */
    fun getPlayerPrefs(player: FcPlayer) = PlayerData(player)

    /**
     * Accesses the player's preferences.
     */
    inner class PlayerData(player: FcPlayer) {
        private val prefs = config["player-data"][player.uuid.toString()]

        /**
         * Whether FastCraft is enabled for this player.
         */
        var fastCraftEnabled: Boolean?
            get() = prefs.getBoolean("fastcraft-enabled")
            set(value) = prefs.setBoolean("fastcraft-enabled", value)
    }
}
