package net.benwoodworth.fastcraft.bukkit.locale

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
