package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor

interface BukkitFcTextColor : FcTextColor {
    val id: String
    val chatColor: ChatColor

    companion object {
        val FcTextColor.id: String
            get() = (this as BukkitFcTextColor).id

        val FcTextColor.chatColor: ChatColor
            get() = (this as BukkitFcTextColor).chatColor
    }

    interface Factory : FcTextColor.Factory {
        fun fromId(id: String): FcTextColor

        fun create(id: String, chatColor: ChatColor): FcTextColor

        companion object {
            fun FcTextColor.Factory.fromId(id: String): FcTextColor {
                return (this as Factory).fromId(id)
            }

            fun FcTextColor.Factory.create(id: String, chatColor: ChatColor): FcTextColor {
                return (this as Factory).create(id, chatColor)
            }
        }
    }
}
