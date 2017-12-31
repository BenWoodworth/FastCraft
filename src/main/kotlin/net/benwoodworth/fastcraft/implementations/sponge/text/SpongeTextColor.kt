package net.benwoodworth.fastcraft.implementations.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.TextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.format.TextColor as Sponge_TextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeTextColor(
        baseTextColor: Sponge_TextColor
) : TextColor, Adapter<Sponge_TextColor>(baseTextColor)
