package net.benwoodworth.fastcraft.platform.impl.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextColor(
        val name: String,
        legacyCodeChar: Char?
) : FcTextColor {

    val legacyCode = legacyCodeChar?.let { "\u00A7$it" } ?: ""

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcTextColor && name == other.name
    }

    override fun hashCode() = name.hashCode()

    override fun toString() = "${javaClass.simpleName}(name=$name)"

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