package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.Config
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player

/**
 * Manages the data config.
 */
class FcDataConfig(
        override var config: Config
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
     * @param player the player
     * @return the player's preferences
     */
    fun getPlayerPrefs(player: Player) = PlayerData(player)

    /**
     * Accesses the player's preferences.
     */
    inner class PlayerData(player: Player) {
        private val prefs = config["player-data"][player.uuid.toString()]

        /**
         * Whether FastCraft is enabled for this player.
         */
        var fastCraftEnabled: Boolean?
            get() = prefs.getBoolean("fastcraft-enabled")
            set(value) = prefs.setBoolean("fastcraft-enabled", value)
    }
}
