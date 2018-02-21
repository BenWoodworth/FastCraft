package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColorRegistry

/**
 * Bukkit implementation of [FcTextColorRegistry].
 */
class BukkitFcTextColorRegistry : FcTextColorRegistry {

    override val reset = BukkitFcTextColor('r')
    override val black = BukkitFcTextColor('0')
    override val darkBlue = BukkitFcTextColor('1')
    override val darkGreen = BukkitFcTextColor('2')
    override val darkAqua = BukkitFcTextColor('3')
    override val darkRed = BukkitFcTextColor('4')
    override val darkPurple = BukkitFcTextColor('5')
    override val gold = BukkitFcTextColor('6')
    override val gray = BukkitFcTextColor('7')
    override val darkGray = BukkitFcTextColor('8')
    override val blue = BukkitFcTextColor('9')
    override val green = BukkitFcTextColor('a')
    override val aqua = BukkitFcTextColor('b')
    override val red = BukkitFcTextColor('c')
    override val lightPurple = BukkitFcTextColor('d')
    override val yellow = BukkitFcTextColor('e')
    override val white = BukkitFcTextColor('f')
}
