package net.benwoodworth.fastcraft.implementations.bukkit.text.nms

import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText

@Suppress("ClassName")
interface BukkitFcText_Nms : BukkitFcText {

    val chatBaseComponent: Any

    interface Factory : BukkitFcText.Factory {

        fun <T : Any> delegateINamable(iNamable: T, name: BukkitFcText_Nms): T
    }

    interface Builder : BukkitFcText.Builder
}