package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcTextColor_1_7 {
    @Singleton
    class TypeClass @Inject constructor(
    ) : BukkitFcTextColor.TypeClass {
        override val FcTextColor.chatColor: ChatColor
            get() = value as ChatColor

        override val FcTextColor.id: String
            get() = when (chatColor) {
                ChatColor.BLACK -> "black"
                ChatColor.DARK_BLUE -> "dark_blue"
                ChatColor.DARK_GREEN -> "dark_green"
                ChatColor.DARK_AQUA -> "dark_aqua"
                ChatColor.DARK_RED -> "dark_red"
                ChatColor.DARK_PURPLE -> "dark_purple"
                ChatColor.GOLD -> "gold"
                ChatColor.GRAY -> "gray"
                ChatColor.DARK_GRAY -> "dark_gray"
                ChatColor.BLUE -> "blue"
                ChatColor.GREEN -> "green"
                ChatColor.AQUA -> "aqua"
                ChatColor.RED -> "red"
                ChatColor.LIGHT_PURPLE -> "light_purple"
                ChatColor.YELLOW -> "yellow"
                ChatColor.WHITE -> "white"
                ChatColor.RESET -> "reset"
                else -> error("$chatColor is not a color")
            }
    }

    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcTextColor.Factory {
        override val black: FcTextColor
            get() = FcTextColor(ChatColor.BLACK)

        override val darkBlue: FcTextColor
            get() = FcTextColor(ChatColor.DARK_BLUE)

        override val darkGreen: FcTextColor
            get() = FcTextColor(ChatColor.DARK_GREEN)

        override val darkAqua: FcTextColor
            get() = FcTextColor(ChatColor.DARK_AQUA)

        override val darkRed: FcTextColor
            get() = FcTextColor(ChatColor.DARK_RED)

        override val darkPurple: FcTextColor
            get() = FcTextColor(ChatColor.DARK_PURPLE)

        override val gold: FcTextColor
            get() = FcTextColor(ChatColor.GOLD)

        override val gray: FcTextColor
            get() = FcTextColor(ChatColor.GRAY)

        override val darkGray: FcTextColor
            get() = FcTextColor(ChatColor.DARK_GRAY)

        override val blue: FcTextColor
            get() = FcTextColor(ChatColor.BLUE)

        override val green: FcTextColor
            get() = FcTextColor(ChatColor.GREEN)

        override val aqua: FcTextColor
            get() = FcTextColor(ChatColor.AQUA)

        override val red: FcTextColor
            get() = FcTextColor(ChatColor.RED)

        override val lightPurple: FcTextColor
            get() = FcTextColor(ChatColor.LIGHT_PURPLE)

        override val yellow: FcTextColor
            get() = FcTextColor(ChatColor.YELLOW)

        override val white: FcTextColor
            get() = FcTextColor(ChatColor.WHITE)

        override val default: FcTextColor
            get() = FcTextColor(ChatColor.RESET)

        override fun fromId(id: String): FcTextColor {
            return when (id) {
                "black" -> black
                "dark_blue" -> darkBlue
                "dark_green" -> darkGreen
                "dark_aqua" -> darkAqua
                "dark_red" -> darkRed
                "dark_purple" -> darkPurple
                "gold" -> gold
                "gray" -> gray
                "dark_gray" -> darkGray
                "blue" -> blue
                "green" -> green
                "aqua" -> aqua
                "red" -> red
                "light_purple" -> lightPurple
                "yellow" -> yellow
                "white" -> white
                "reset" -> default
                else -> throw IllegalArgumentException("Invalid color ID: $id")
            }
        }
    }
}
