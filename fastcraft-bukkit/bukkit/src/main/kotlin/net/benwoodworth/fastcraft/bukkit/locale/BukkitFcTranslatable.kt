package net.benwoodworth.fastcraft.bukkit.locale

import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

interface BukkitFcTranslatable : FcTranslatable {

    val key: String

    fun translate(locale: FcLocale): String?
}
