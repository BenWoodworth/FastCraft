package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextColor(
        val id: String
) : FcTextColor {

    class Factory @Inject constructor(
    ) : FcTextColor.Factory {

        override val black = BukkitFcTextColor("black")
        override val darkBlue = BukkitFcTextColor("dark_blue")
        override val darkGreen = BukkitFcTextColor("dark_green")
        override val darkAqua = BukkitFcTextColor("dark_aqua")
        override val darkRed = BukkitFcTextColor("dark_red")
        override val darkPurple = BukkitFcTextColor("dark_purple")
        override val gold = BukkitFcTextColor("gold")
        override val gray = BukkitFcTextColor("gray")
        override val darkGray = BukkitFcTextColor("dark_gray")
        override val blue = BukkitFcTextColor("blue")
        override val green = BukkitFcTextColor("green")
        override val aqua = BukkitFcTextColor("aqua")
        override val red = BukkitFcTextColor("red")
        override val lightPurple = BukkitFcTextColor("light_purple")
        override val yellow = BukkitFcTextColor("yellow")
        override val white = BukkitFcTextColor("white")
        override val reset = BukkitFcTextColor("reset")
        override val none = BukkitFcTextColor("none")
    }
}