package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.TextColor
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.text.format.TextColor as SpongeTextColor

/**
 * Adapts Sponge text colors.
 */
class SpongeTextColorAdapter(
        baseTextColor: SpongeTextColor
) : TextColor, Adapter<SpongeTextColor>(baseTextColor) {

    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hashCode(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
