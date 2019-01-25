package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText

interface BukkitFcTextConverter {

    fun FcText.toLegacy(): String

    fun FcText.toRaw(): String
}