package net.benwoodworth.fastcraft.impl.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.TextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.format.TextColor as Sponge_TextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeTextColor(
        baseTextColor: Sponge_TextColor
) : TextColor, Adapter<Sponge_TextColor>(baseTextColor)
