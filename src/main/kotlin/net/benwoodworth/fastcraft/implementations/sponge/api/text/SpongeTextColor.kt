package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.TextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.format.TextColor as Sponge_TextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeTextColor(
        baseTextColor: Sponge_TextColor
) : TextColor, Adapter<Sponge_TextColor>(baseTextColor)
