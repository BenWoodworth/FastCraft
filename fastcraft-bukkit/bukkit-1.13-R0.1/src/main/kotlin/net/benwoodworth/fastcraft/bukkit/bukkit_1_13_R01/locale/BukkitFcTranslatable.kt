package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.locale

import net.benwoodworth.fastcraft.platform.locale.FcLocale

class BukkitFcTranslatable(
    val key: String,
    val default: String?
) : BukkitFcTranslatable {

    override fun translate(locale: FcLocale?): String? {
        return default // TODO
    }
}
