package net.benwoodworth.fastcraft.implementations.bukkit.text.spigot

import net.benwoodworth.fastcraft.dependencies.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import net.md_5.bungee.api.ChatColor

class BukkitFcTextColor_Spigot(
        override val base: ChatColor?
) : Adapter<ChatColor?>(), FcTextColor {

    class Catalog : FcTextColor.Catalog {
        override val black: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.BLACK)
        override val darkBlue: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_BLUE)
        override val darkGreen: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_GREEN)
        override val darkAqua: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_AQUA)
        override val darkRed: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_RED)
        override val darkPurple: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_PURPLE)
        override val gold: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.GOLD)
        override val gray: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.GRAY)
        override val darkGray: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.DARK_GRAY)
        override val blue: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.BLUE)
        override val green: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.GREEN)
        override val aqua: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.AQUA)
        override val red: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.RED)
        override val lightPurple: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.LIGHT_PURPLE)
        override val yellow: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.YELLOW)
        override val white: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.WHITE)
        override val reset: FcTextColor = BukkitFcTextColor_Spigot(ChatColor.RESET)
        override val none: FcTextColor = BukkitFcTextColor_Spigot(null)
    }
}