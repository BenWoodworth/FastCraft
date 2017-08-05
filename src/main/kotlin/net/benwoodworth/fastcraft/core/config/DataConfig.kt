package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.config.Config
import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * Manages the data config.
 */
class DataConfig(
        override var config: Config
) : ConfigWrapper {

    var revision: Int?
        get() = config.getInt("config-revision")
        set(value) = config.setInt("config-revision", value)

    fun getPlayerPrefs(player: Player) = PlayerPrefs(player)

    inner class PlayerPrefs(player: Player) {
        private val prefs = config[player.uuid.toString()]

        var fastCraftEnabled: Boolean
            get() = prefs.getBoolean("fastcraft-enabled")!!
            set(value) = prefs.setBoolean("fastcraft-enabled", value)
    }
}
