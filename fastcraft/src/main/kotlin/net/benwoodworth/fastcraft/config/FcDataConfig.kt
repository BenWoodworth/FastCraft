//package net.benwoodworth.fastcraft.config
//
//import net.benwoodworth.fastcraft.platform.player.FcPlayer
//import net.benwoodworth.fastcraft.platform.config.FcConfig
//
//class FcDataConfig(
//    override var config: FcConfig
//) : net.benwoodworth.fastcraft.config.ConfigWrapper {
//
//    var revision: Int?
//        get() = config.getInt("config-revision")
//        set(value) = config.setInt("config-revision", value)
//
//    fun getPlayerPrefs(player: FcPlayer) = PlayerData(player)
//
//    inner class PlayerData(player: FcPlayer) {
//        private val prefs = config["player-data"][player.uuid.toString()]
//
//        var fastCraftEnabled: Boolean?
//            get() = prefs.getBoolean("fastcraft-enabled")
//            set(value) = prefs.setBoolean("fastcraft-enabled", value)
//    }
//}
