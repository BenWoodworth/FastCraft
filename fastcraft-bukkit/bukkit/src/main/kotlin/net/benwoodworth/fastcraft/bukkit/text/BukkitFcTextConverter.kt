package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText

interface BukkitFcTextConverter {

    fun FcText.toRaw(): String
}