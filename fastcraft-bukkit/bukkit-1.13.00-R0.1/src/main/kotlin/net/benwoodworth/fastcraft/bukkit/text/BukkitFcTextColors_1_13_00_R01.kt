package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor

class BukkitFcTextColors_1_13_00_R01 : BukkitFcTextColors {

    override val black = BukkitFcTextColor_1_13_00_R01("black", "§0")
    override val darkBlue = BukkitFcTextColor_1_13_00_R01("dark_blue", "§1")
    override val darkGreen = BukkitFcTextColor_1_13_00_R01("dark_green", "§2")
    override val darkAqua = BukkitFcTextColor_1_13_00_R01("dark_aqua", "§3")
    override val darkRed = BukkitFcTextColor_1_13_00_R01("dark_red", "§4")
    override val darkPurple = BukkitFcTextColor_1_13_00_R01("dark_purple", "§5")
    override val gold = BukkitFcTextColor_1_13_00_R01("gold", "§6")
    override val gray = BukkitFcTextColor_1_13_00_R01("gray", "§7")
    override val darkGray = BukkitFcTextColor_1_13_00_R01("dark_gray", "§8")
    override val blue = BukkitFcTextColor_1_13_00_R01("blue", "§9")
    override val green = BukkitFcTextColor_1_13_00_R01("green", "§a")
    override val aqua = BukkitFcTextColor_1_13_00_R01("aqua", "§b")
    override val red = BukkitFcTextColor_1_13_00_R01("red", "§c")
    override val lightPurple = BukkitFcTextColor_1_13_00_R01("light_purple", "§d")
    override val yellow = BukkitFcTextColor_1_13_00_R01("yellow", "§e")
    override val white = BukkitFcTextColor_1_13_00_R01("white", "§f")
    override val default = BukkitFcTextColor_1_13_00_R01("reset", "§r")

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
