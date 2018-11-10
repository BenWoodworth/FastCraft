//package net.benwoodworth.fastcraft.config
//
//import net.benwoodworth.fastcraft.platform.config.FcConfig
//
//class FcPluginConfig(
//    override var config: FcConfig
//) : net.benwoodworth.fastcraft.config.ConfigWrapper {
//
//    var language: String
//        get() = config.getString("language")!!
//        set(value) = config.setString("language", value)
//
//    var hiddenRecipes: List<String>
//        get() = config.getStringList("disabled-recipes")!!
//        set(value) = config.setStringList("disabled-recipes", value)
//}
