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
}
