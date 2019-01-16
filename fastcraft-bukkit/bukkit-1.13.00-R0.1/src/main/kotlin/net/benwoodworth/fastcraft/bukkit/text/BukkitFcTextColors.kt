package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors

class BukkitFcTextColors : FcTextColors {

    override val black = BukkitFcTextColor("black", "§0")
    override val darkBlue = BukkitFcTextColor("dark_blue", "§1")
    override val darkGreen = BukkitFcTextColor("dark_green", "§2")
    override val darkAqua = BukkitFcTextColor("dark_aqua", "§3")
    override val darkRed = BukkitFcTextColor("dark_red", "§4")
    override val darkPurple = BukkitFcTextColor("dark_purple", "§5")
    override val gold = BukkitFcTextColor("gold", "§6")
    override val gray = BukkitFcTextColor("gray", "§7")
    override val darkGray = BukkitFcTextColor("dark_gray", "§8")
    override val blue = BukkitFcTextColor("blue", "§9")
    override val green = BukkitFcTextColor("green", "§a")
    override val aqua = BukkitFcTextColor("aqua", "§b")
    override val red = BukkitFcTextColor("red", "§c")
    override val lightPurple = BukkitFcTextColor("light_purple", "§d")
    override val yellow = BukkitFcTextColor("yellow", "§e")
    override val white = BukkitFcTextColor("white", "§f")
    override val default = BukkitFcTextColor("reset", "§r")

    fun fromId(id: String): FcTextColor {
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
