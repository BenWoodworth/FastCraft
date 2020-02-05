package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*

interface BukkitFcTextConverter {
    fun toRaw(text: FcText): String

    fun toLegacy(text: FcText, locale: Locale): String
}
