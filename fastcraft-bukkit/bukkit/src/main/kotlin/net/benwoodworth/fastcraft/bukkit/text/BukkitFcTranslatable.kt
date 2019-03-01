package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcTranslatable

interface BukkitFcTranslatable : FcTranslatable {

    val key: String

    fun translate(locale: FcLocale): String?
}
