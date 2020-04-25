package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import org.bukkit.ChatColor

interface BukkitFcTextColors : FcTextColors {
    fun fromId(id: String): FcTextColor

    fun create(id: String, chatColor: ChatColor): FcTextColor

    companion object {
        fun FcTextColors.fromId(id: String): FcTextColor {
            return (this as BukkitFcTextColors).fromId(id)
        }

        fun FcTextColors.create(id: String, chatColor: ChatColor): FcTextColor {
            return (this as BukkitFcTextColors).create(id, chatColor)
        }
    }
}
