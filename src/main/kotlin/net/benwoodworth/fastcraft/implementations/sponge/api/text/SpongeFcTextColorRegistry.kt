package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColorRegistry
import org.spongepowered.api.text.format.TextColors as Sponge_TextColors

/**
 * Registry of Sponge text colors.
 */
class SpongeFcTextColorRegistry : FcTextColorRegistry {

    override val reset = SpongeFcTextColor(Sponge_TextColors.RESET)
    override val black = SpongeFcTextColor(Sponge_TextColors.BLACK)
    override val darkBlue = SpongeFcTextColor(Sponge_TextColors.DARK_BLUE)
    override val darkGreen = SpongeFcTextColor(Sponge_TextColors.DARK_GREEN)
    override val darkAqua = SpongeFcTextColor(Sponge_TextColors.DARK_AQUA)
    override val darkRed = SpongeFcTextColor(Sponge_TextColors.DARK_RED)
    override val darkPurple = SpongeFcTextColor(Sponge_TextColors.DARK_PURPLE)
    override val gold = SpongeFcTextColor(Sponge_TextColors.GOLD)
    override val gray = SpongeFcTextColor(Sponge_TextColors.GRAY)
    override val darkGray = SpongeFcTextColor(Sponge_TextColors.DARK_GRAY)
    override val blue = SpongeFcTextColor(Sponge_TextColors.BLUE)
    override val green = SpongeFcTextColor(Sponge_TextColors.GREEN)
    override val aqua = SpongeFcTextColor(Sponge_TextColors.AQUA)
    override val red = SpongeFcTextColor(Sponge_TextColors.RED)
    override val lightPurple = SpongeFcTextColor(Sponge_TextColors.LIGHT_PURPLE)
    override val yellow = SpongeFcTextColor(Sponge_TextColors.YELLOW)
    override val white = SpongeFcTextColor(Sponge_TextColors.WHITE)
}
