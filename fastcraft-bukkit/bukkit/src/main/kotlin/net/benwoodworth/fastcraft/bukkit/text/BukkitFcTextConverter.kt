package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale

interface BukkitFcTextConverter {

    fun BukkitFcText.toRaw(): String

    fun BukkitFcText.toLegacy(locale: FcLocale): String
}