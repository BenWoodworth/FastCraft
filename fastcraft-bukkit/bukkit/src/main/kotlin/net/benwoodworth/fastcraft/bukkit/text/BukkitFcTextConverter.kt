package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText


interface BukkitFcTextConverter {

    fun toRaw(): String

    fun toLegacy(): String

    fun fromRaw(): FcText

    fun fromLegacy(): FcText
}
