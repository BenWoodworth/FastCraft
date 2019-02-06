package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor

interface BukkitFcTextColor : FcTextColor {

    val id: String

    val chatColor: ChatColor
}
