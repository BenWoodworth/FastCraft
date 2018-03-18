package net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit

import net.benwoodworth.fastcraft.dependencies.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextColor_Bukkit(
        val id: String,
        val legacyCode: Char?
) : FcTextColor {

    class Catalog @Inject constructor(
    ) : FcTextColor.Catalog {

        override val black = BukkitFcTextColor_Bukkit("black", '0')
        override val darkBlue = BukkitFcTextColor_Bukkit("dark_blue", '1')
        override val darkGreen = BukkitFcTextColor_Bukkit("dark_green", '2')
        override val darkAqua = BukkitFcTextColor_Bukkit("dark_aqua", '3')
        override val darkRed = BukkitFcTextColor_Bukkit("dark_red", '4')
        override val darkPurple = BukkitFcTextColor_Bukkit("dark_purple", '5')
        override val gold = BukkitFcTextColor_Bukkit("gold", '6')
        override val gray = BukkitFcTextColor_Bukkit("gray", '7')
        override val darkGray = BukkitFcTextColor_Bukkit("dark_gray", '8')
        override val blue = BukkitFcTextColor_Bukkit("blue", '9')
        override val green = BukkitFcTextColor_Bukkit("green", 'a')
        override val aqua = BukkitFcTextColor_Bukkit("aqua", 'b')
        override val red = BukkitFcTextColor_Bukkit("red", 'c')
        override val lightPurple = BukkitFcTextColor_Bukkit("light_purple", 'd')
        override val yellow = BukkitFcTextColor_Bukkit("yellow", 'e')
        override val white = BukkitFcTextColor_Bukkit("white", 'f')
        override val reset = BukkitFcTextColor_Bukkit("reset", 'r')
        override val none = BukkitFcTextColor_Bukkit("none", null)
    }
}