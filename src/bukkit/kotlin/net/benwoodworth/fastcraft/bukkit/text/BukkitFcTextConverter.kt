package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText

interface BukkitFcTextConverter {
    fun toRaw(text: FcText): String

    fun toLegacy(text: FcText, locale: FcLocale): String
}
