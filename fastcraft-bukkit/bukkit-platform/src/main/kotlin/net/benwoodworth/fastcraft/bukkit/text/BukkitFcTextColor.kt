package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor

object BukkitFcTextColor {
    interface TypeClass : FcTextColor.TypeClass {
        val FcTextColor.chatColor: ChatColor
        val FcTextColor.id: String
    }

    interface Factory : FcTextColor.Factory {
        fun fromId(id: String): FcTextColor
    }
}

val FcTextColor.TypeClass.bukkit: BukkitFcTextColor.TypeClass
    get() = this as BukkitFcTextColor.TypeClass

fun FcTextColor.Factory.fromId(id: String): FcTextColor {
    return (this as BukkitFcTextColor.Factory).fromId(id)
}

fun FcTextColor.Factory.create(id: String, chatColor: ChatColor): FcTextColor {
    return (this as BukkitFcTextColor.Factory).create(id, chatColor)
}
