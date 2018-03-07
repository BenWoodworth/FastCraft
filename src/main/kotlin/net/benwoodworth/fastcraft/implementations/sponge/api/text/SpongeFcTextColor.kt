package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.format.TextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeFcTextColor(
        override val base: TextColor
) : FcTextColor, Adapter<TextColor>()
