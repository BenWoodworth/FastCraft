package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.TextColorRegistry
import org.spongepowered.api.text.format.TextColors as SpongeTextColors

/**
 * Registry of Sponge text colors.
 */
class SpongeTextColorRegistry : TextColorRegistry {

    override val none = SpongeTextColorAdapter(SpongeTextColors.NONE)
    override val reset = SpongeTextColorAdapter(SpongeTextColors.RESET)
    override val black = SpongeTextColorAdapter(SpongeTextColors.BLACK)
    override val darkBlue = SpongeTextColorAdapter(SpongeTextColors.DARK_BLUE)
    override val darkGreen = SpongeTextColorAdapter(SpongeTextColors.DARK_GREEN)
    override val darkAqua = SpongeTextColorAdapter(SpongeTextColors.DARK_AQUA)
    override val darkRed = SpongeTextColorAdapter(SpongeTextColors.DARK_RED)
    override val darkPurple = SpongeTextColorAdapter(SpongeTextColors.DARK_PURPLE)
    override val gold = SpongeTextColorAdapter(SpongeTextColors.GOLD)
    override val gray = SpongeTextColorAdapter(SpongeTextColors.GRAY)
    override val darkGray = SpongeTextColorAdapter(SpongeTextColors.DARK_GRAY)
    override val blue = SpongeTextColorAdapter(SpongeTextColors.BLUE)
    override val green = SpongeTextColorAdapter(SpongeTextColors.GREEN)
    override val aqua = SpongeTextColorAdapter(SpongeTextColors.AQUA)
    override val red = SpongeTextColorAdapter(SpongeTextColors.RED)
    override val lightPurple = SpongeTextColorAdapter(SpongeTextColors.LIGHT_PURPLE)
    override val yellow = SpongeTextColorAdapter(SpongeTextColors.YELLOW)
    override val white = SpongeTextColorAdapter(SpongeTextColors.WHITE)
}
