package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.TextColorRegistry

/**
 * Bukkit implementation of [TextColorRegistry].
 */
class BukkitTextColorRegistry : TextColorRegistry {

    override val reset = BukkitTextColor('r')
    override val black = BukkitTextColor('0')
    override val darkBlue = BukkitTextColor('1')
    override val darkGreen = BukkitTextColor('2')
    override val darkAqua = BukkitTextColor('3')
    override val darkRed = BukkitTextColor('4')
    override val darkPurple = BukkitTextColor('5')
    override val gold = BukkitTextColor('6')
    override val gray = BukkitTextColor('7')
    override val darkGray = BukkitTextColor('8')
    override val blue = BukkitTextColor('9')
    override val green = BukkitTextColor('a')
    override val aqua = BukkitTextColor('b')
    override val red = BukkitTextColor('c')
    override val lightPurple = BukkitTextColor('d')
    override val yellow = BukkitTextColor('e')
    override val white = BukkitTextColor('f')
}
