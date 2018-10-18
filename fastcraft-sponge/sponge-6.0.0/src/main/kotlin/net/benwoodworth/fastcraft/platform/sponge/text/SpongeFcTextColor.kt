package net.benwoodworth.fastcraft.platform.sponge.text

import net.benwoodworth.fastcraft.platform.api.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.format.TextColor
import org.spongepowered.api.text.format.TextColors
import javax.inject.Inject

class SpongeFcTextColor(
    override val base: TextColor
) : Adapter<TextColor>(), FcTextColor {

    class Catalog @Inject constructor(
    ) : FcTextColor.Catalog {

        override val black: FcTextColor = SpongeFcTextColor(TextColors.BLACK)
        override val darkBlue: FcTextColor = SpongeFcTextColor(TextColors.DARK_BLUE)
        override val darkGreen: FcTextColor = SpongeFcTextColor(TextColors.DARK_GREEN)
        override val darkAqua: FcTextColor = SpongeFcTextColor(TextColors.DARK_AQUA)
        override val darkRed: FcTextColor = SpongeFcTextColor(TextColors.DARK_RED)
        override val darkPurple: FcTextColor = SpongeFcTextColor(TextColors.DARK_PURPLE)
        override val gold: FcTextColor = SpongeFcTextColor(TextColors.GOLD)
        override val gray: FcTextColor = SpongeFcTextColor(TextColors.GRAY)
        override val darkGray: FcTextColor = SpongeFcTextColor(TextColors.DARK_GRAY)
        override val blue: FcTextColor = SpongeFcTextColor(TextColors.BLUE)
        override val green: FcTextColor = SpongeFcTextColor(TextColors.GREEN)
        override val aqua: FcTextColor = SpongeFcTextColor(TextColors.AQUA)
        override val red: FcTextColor = SpongeFcTextColor(TextColors.RED)
        override val lightPurple: FcTextColor = SpongeFcTextColor(TextColors.LIGHT_PURPLE)
        override val yellow: FcTextColor = SpongeFcTextColor(TextColors.YELLOW)
        override val white: FcTextColor = SpongeFcTextColor(TextColors.WHITE)
        override val reset: FcTextColor = SpongeFcTextColor(TextColors.RESET)
        override val none: FcTextColor = SpongeFcTextColor(TextColors.NONE)
    }
}
