package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor
import javax.inject.Inject

class BukkitFcTextColors_1_13_00_R01 @Inject constructor(
) : BukkitFcTextColors {

    override val black = BukkitFcTextColor_1_13_00_R01("black", ChatColor.BLACK)
    override val darkBlue = BukkitFcTextColor_1_13_00_R01("dark_blue", ChatColor.DARK_BLUE)
    override val darkGreen = BukkitFcTextColor_1_13_00_R01("dark_green", ChatColor.DARK_GREEN)
    override val darkAqua = BukkitFcTextColor_1_13_00_R01("dark_aqua", ChatColor.DARK_AQUA)
    override val darkRed = BukkitFcTextColor_1_13_00_R01("dark_red", ChatColor.DARK_RED)
    override val darkPurple = BukkitFcTextColor_1_13_00_R01("dark_purple", ChatColor.DARK_PURPLE)
    override val gold = BukkitFcTextColor_1_13_00_R01("gold", ChatColor.GOLD)
    override val gray = BukkitFcTextColor_1_13_00_R01("gray", ChatColor.GRAY)
    override val darkGray = BukkitFcTextColor_1_13_00_R01("dark_gray", ChatColor.DARK_GRAY)
    override val blue = BukkitFcTextColor_1_13_00_R01("blue", ChatColor.BLUE)
    override val green = BukkitFcTextColor_1_13_00_R01("green", ChatColor.GREEN)
    override val aqua = BukkitFcTextColor_1_13_00_R01("aqua", ChatColor.AQUA)
    override val red = BukkitFcTextColor_1_13_00_R01("red", ChatColor.RED)
    override val lightPurple = BukkitFcTextColor_1_13_00_R01("light_purple", ChatColor.LIGHT_PURPLE)
    override val yellow = BukkitFcTextColor_1_13_00_R01("yellow", ChatColor.YELLOW)
    override val white = BukkitFcTextColor_1_13_00_R01("white", ChatColor.WHITE)
    override val default = BukkitFcTextColor_1_13_00_R01("reset", ChatColor.RESET)

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
