package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.TextColorRegistry
import org.spongepowered.api.text.format.TextColors as SpongeTextColors

/**
 * Registry of Sponge text colors.
 */
class SpongeTextColorRegistry : TextColorRegistry {

    override val none = SpongeTextColor(SpongeTextColors.NONE)
    override val reset = SpongeTextColor(SpongeTextColors.RESET)
    override val black = SpongeTextColor(SpongeTextColors.BLACK)
    override val darkBlue = SpongeTextColor(SpongeTextColors.DARK_BLUE)
    override val darkGreen = SpongeTextColor(SpongeTextColors.DARK_GREEN)
    override val darkAqua = SpongeTextColor(SpongeTextColors.DARK_AQUA)
    override val darkRed = SpongeTextColor(SpongeTextColors.DARK_RED)
    override val darkPurple = SpongeTextColor(SpongeTextColors.DARK_PURPLE)
    override val gold = SpongeTextColor(SpongeTextColors.GOLD)
    override val gray = SpongeTextColor(SpongeTextColors.GRAY)
    override val darkGray = SpongeTextColor(SpongeTextColors.DARK_GRAY)
    override val blue = SpongeTextColor(SpongeTextColors.BLUE)
    override val green = SpongeTextColor(SpongeTextColors.GREEN)
    override val aqua = SpongeTextColor(SpongeTextColors.AQUA)
    override val red = SpongeTextColor(SpongeTextColors.RED)
    override val lightPurple = SpongeTextColor(SpongeTextColors.LIGHT_PURPLE)
    override val yellow = SpongeTextColor(SpongeTextColors.YELLOW)
    override val white = SpongeTextColor(SpongeTextColors.WHITE)
}
