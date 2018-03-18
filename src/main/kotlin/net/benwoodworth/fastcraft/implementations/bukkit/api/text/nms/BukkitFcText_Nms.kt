package net.benwoodworth.fastcraft.implementations.bukkit.api.text.nms

import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcText

@Suppress("ClassName")
interface BukkitFcText_Nms : BukkitFcText {

    val component: Any

    interface Factory : BukkitFcText.Factory

    interface Builder : BukkitFcText.Builder
}