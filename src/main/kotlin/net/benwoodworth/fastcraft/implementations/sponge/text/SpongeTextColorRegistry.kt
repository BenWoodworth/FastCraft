package net.benwoodworth.fastcraft.implementations.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.TextColorRegistry
import org.spongepowered.api.text.format.TextColors as Sponge_TextColors

/**
 * Registry of Sponge text colors.
 */
class SpongeTextColorRegistry : TextColorRegistry {

    override val reset = SpongeTextColor(Sponge_TextColors.RESET)
    override val black = SpongeTextColor(Sponge_TextColors.BLACK)
    override val darkBlue = SpongeTextColor(Sponge_TextColors.DARK_BLUE)
    override val darkGreen = SpongeTextColor(Sponge_TextColors.DARK_GREEN)
    override val darkAqua = SpongeTextColor(Sponge_TextColors.DARK_AQUA)
    override val darkRed = SpongeTextColor(Sponge_TextColors.DARK_RED)
    override val darkPurple = SpongeTextColor(Sponge_TextColors.DARK_PURPLE)
    override val gold = SpongeTextColor(Sponge_TextColors.GOLD)
    override val gray = SpongeTextColor(Sponge_TextColors.GRAY)
    override val darkGray = SpongeTextColor(Sponge_TextColors.DARK_GRAY)
    override val blue = SpongeTextColor(Sponge_TextColors.BLUE)
    override val green = SpongeTextColor(Sponge_TextColors.GREEN)
    override val aqua = SpongeTextColor(Sponge_TextColors.AQUA)
    override val red = SpongeTextColor(Sponge_TextColors.RED)
    override val lightPurple = SpongeTextColor(Sponge_TextColors.LIGHT_PURPLE)
    override val yellow = SpongeTextColor(Sponge_TextColors.YELLOW)
    override val white = SpongeTextColor(Sponge_TextColors.WHITE)
}
