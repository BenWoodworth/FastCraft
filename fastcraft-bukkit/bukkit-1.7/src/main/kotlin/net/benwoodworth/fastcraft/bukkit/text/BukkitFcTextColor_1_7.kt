package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextColor
import org.bukkit.ChatColor
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcTextColor_1_7(
    override val id: String,
    override val chatColor: ChatColor,
) : BukkitFcTextColor {
    override fun equals(other: Any?): Boolean {
        return other is FcTextColor && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcTextColor.Factory {
        override val black: FcTextColor by lazy { create("black", ChatColor.BLACK) }
        override val darkBlue: FcTextColor by lazy { create("dark_blue", ChatColor.DARK_BLUE) }
        override val darkGreen: FcTextColor by lazy { create("dark_green", ChatColor.DARK_GREEN) }
        override val darkAqua: FcTextColor by lazy { create("dark_aqua", ChatColor.DARK_AQUA) }
        override val darkRed: FcTextColor by lazy { create("dark_red", ChatColor.DARK_RED) }
        override val darkPurple: FcTextColor by lazy { create("dark_purple", ChatColor.DARK_PURPLE) }
        override val gold: FcTextColor by lazy { create("gold", ChatColor.GOLD) }
        override val gray: FcTextColor by lazy { create("gray", ChatColor.GRAY) }
        override val darkGray: FcTextColor by lazy { create("dark_gray", ChatColor.DARK_GRAY) }
        override val blue: FcTextColor by lazy { create("blue", ChatColor.BLUE) }
        override val green: FcTextColor by lazy { create("green", ChatColor.GREEN) }
        override val aqua: FcTextColor by lazy { create("aqua", ChatColor.AQUA) }
        override val red: FcTextColor by lazy { create("red", ChatColor.RED) }
        override val lightPurple: FcTextColor by lazy { create("light_purple", ChatColor.LIGHT_PURPLE) }
        override val yellow: FcTextColor by lazy { create("yellow", ChatColor.YELLOW) }
        override val white: FcTextColor by lazy { create("white", ChatColor.WHITE) }
        override val default: FcTextColor by lazy { create("reset", ChatColor.RESET) }

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

        override fun create(id: String, chatColor: ChatColor): FcTextColor {
            return BukkitFcTextColor_1_7(id, chatColor)
        }
    }
}
