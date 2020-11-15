package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor

object BukkitFcTextColor {
    interface Operations : FcTextColor.Operations {
        val FcTextColor.chatColor: ChatColor
        val FcTextColor.id: String
    }

    interface Factory : FcTextColor.Factory {
        fun fromId(id: String): FcTextColor
    }
}

val FcTextColor.Operations.bukkit: BukkitFcTextColor.Operations
    get() = this as BukkitFcTextColor.Operations

fun FcTextColor.Factory.fromId(id: String): FcTextColor {
    return (this as BukkitFcTextColor.Factory).fromId(id)
}

fun FcTextColor.Factory.create(id: String, chatColor: ChatColor): FcTextColor {
    return (this as BukkitFcTextColor.Factory).create(id, chatColor)
}
