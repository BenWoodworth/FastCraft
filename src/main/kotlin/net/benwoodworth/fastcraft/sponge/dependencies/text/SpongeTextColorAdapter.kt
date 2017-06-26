package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.TextColor
import net.benwoodworth.fastcraft.core.util.Adapter
import org.spongepowered.api.text.format.TextColor as SpongeTextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeTextColorAdapter(
        baseTextColor: SpongeTextColor
) : TextColor, Adapter<SpongeTextColor>(baseTextColor)
