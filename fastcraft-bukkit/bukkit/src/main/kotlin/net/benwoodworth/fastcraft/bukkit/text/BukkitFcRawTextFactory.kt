package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText

interface BukkitFcRawTextFactory {

    fun createBukkitFcRawText(text: FcText): BukkitFcRawText
}