package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale

interface BukkitLocalizer {

    fun localize(key: String, locale: FcLocale): String?
}