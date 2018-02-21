package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColorRegistry
import org.spongepowered.api.text.format.TextColors

/**
 * Registry of Sponge text colors.
 */
class SpongeFcTextColorRegistry : FcTextColorRegistry {

    override val reset = SpongeFcTextColor(TextColors.RESET)
    override val black = SpongeFcTextColor(TextColors.BLACK)
    override val darkBlue = SpongeFcTextColor(TextColors.DARK_BLUE)
    override val darkGreen = SpongeFcTextColor(TextColors.DARK_GREEN)
    override val darkAqua = SpongeFcTextColor(TextColors.DARK_AQUA)
    override val darkRed = SpongeFcTextColor(TextColors.DARK_RED)
    override val darkPurple = SpongeFcTextColor(TextColors.DARK_PURPLE)
    override val gold = SpongeFcTextColor(TextColors.GOLD)
    override val gray = SpongeFcTextColor(TextColors.GRAY)
    override val darkGray = SpongeFcTextColor(TextColors.DARK_GRAY)
    override val blue = SpongeFcTextColor(TextColors.BLUE)
    override val green = SpongeFcTextColor(TextColors.GREEN)
    override val aqua = SpongeFcTextColor(TextColors.AQUA)
    override val red = SpongeFcTextColor(TextColors.RED)
    override val lightPurple = SpongeFcTextColor(TextColors.LIGHT_PURPLE)
    override val yellow = SpongeFcTextColor(TextColors.YELLOW)
    override val white = SpongeFcTextColor(TextColors.WHITE)
}
