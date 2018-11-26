package net.benwoodworth.fastcraft.bukkit.locale

import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

@Suppress("ClassName")
class Bukkit_11300R01_FcTranslatable(
    val key: String,
    val default: String
) : FcTranslatable {

    override fun translate(): String {
        return default
    }

    override fun translate(locale: FcLocale): String {
        return "[$locale/$key]" // TODO
    }
}
