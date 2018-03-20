package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextColor(
        val id: String,
        val legacyCode: Char?
) : FcTextColor {

    class Catalog @Inject constructor(
    ) : FcTextColor.Catalog {

        override val black = BukkitFcTextColor("black", '0')
        override val darkBlue = BukkitFcTextColor("dark_blue", '1')
        override val darkGreen = BukkitFcTextColor("dark_green", '2')
        override val darkAqua = BukkitFcTextColor("dark_aqua", '3')
        override val darkRed = BukkitFcTextColor("dark_red", '4')
        override val darkPurple = BukkitFcTextColor("dark_purple", '5')
        override val gold = BukkitFcTextColor("gold", '6')
        override val gray = BukkitFcTextColor("gray", '7')
        override val darkGray = BukkitFcTextColor("dark_gray", '8')
        override val blue = BukkitFcTextColor("blue", '9')
        override val green = BukkitFcTextColor("green", 'a')
        override val aqua = BukkitFcTextColor("aqua", 'b')
        override val red = BukkitFcTextColor("red", 'c')
        override val lightPurple = BukkitFcTextColor("light_purple", 'd')
        override val yellow = BukkitFcTextColor("yellow", 'e')
        override val white = BukkitFcTextColor("white", 'f')
        override val reset = BukkitFcTextColor("reset", 'r')
        override val none = BukkitFcTextColor("none", null)
    }
}