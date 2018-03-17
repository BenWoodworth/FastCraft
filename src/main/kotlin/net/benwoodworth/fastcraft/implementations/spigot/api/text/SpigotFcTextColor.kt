package net.benwoodworth.fastcraft.implementations.spigot.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import net.md_5.bungee.api.ChatColor

class SpigotFcTextColor(
        override val base: ChatColor?
) : Adapter<ChatColor?>(), FcTextColor {

    class Catalog : FcTextColor.Catalog {
        override val black: FcTextColor = SpigotFcTextColor(ChatColor.BLACK)
        override val darkBlue: FcTextColor = SpigotFcTextColor(ChatColor.DARK_BLUE)
        override val darkGreen: FcTextColor = SpigotFcTextColor(ChatColor.DARK_GREEN)
        override val darkAqua: FcTextColor = SpigotFcTextColor(ChatColor.DARK_AQUA)
        override val darkRed: FcTextColor = SpigotFcTextColor(ChatColor.DARK_RED)
        override val darkPurple: FcTextColor = SpigotFcTextColor(ChatColor.DARK_PURPLE)
        override val gold: FcTextColor = SpigotFcTextColor(ChatColor.GOLD)
        override val gray: FcTextColor = SpigotFcTextColor(ChatColor.GRAY)
        override val darkGray: FcTextColor = SpigotFcTextColor(ChatColor.DARK_GRAY)
        override val blue: FcTextColor = SpigotFcTextColor(ChatColor.BLUE)
        override val green: FcTextColor = SpigotFcTextColor(ChatColor.GREEN)
        override val aqua: FcTextColor = SpigotFcTextColor(ChatColor.AQUA)
        override val red: FcTextColor = SpigotFcTextColor(ChatColor.RED)
        override val lightPurple: FcTextColor = SpigotFcTextColor(ChatColor.LIGHT_PURPLE)
        override val yellow: FcTextColor = SpigotFcTextColor(ChatColor.YELLOW)
        override val white: FcTextColor = SpigotFcTextColor(ChatColor.WHITE)
        override val reset: FcTextColor = SpigotFcTextColor(ChatColor.RESET)
        override val none: FcTextColor = SpigotFcTextColor(null)
    }
}