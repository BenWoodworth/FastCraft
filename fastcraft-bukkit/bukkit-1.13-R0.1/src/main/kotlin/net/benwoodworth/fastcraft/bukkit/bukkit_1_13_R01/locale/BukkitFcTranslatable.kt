package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.locale

import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

class BukkitFcTranslatable(
    val key: String,
    val default: String?
) : FcTranslatable {

    override fun translate(locale: FcLocale?): String? {
        return default // TODO
    }
}
