package net.benwoodworth.fastcraft.bukkit.text

import java.util.*

interface BukkitLocalizer {
    fun localize(key: String, locale: Locale): String?
}
