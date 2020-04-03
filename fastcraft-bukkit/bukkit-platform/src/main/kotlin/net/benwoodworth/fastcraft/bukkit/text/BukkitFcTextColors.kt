package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors

interface BukkitFcTextColors : FcTextColors {
    fun fromId(id: String): FcTextColor

    companion object {
        fun FcTextColors.fromId(id: String): FcTextColor {
            return (this as BukkitFcTextColors).fromId(id)
        }
    }
}
