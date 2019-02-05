package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText

interface BukkitFcRawTextFactory {

    fun BukkitFcRawText(text: FcText): BukkitFcRawText
}