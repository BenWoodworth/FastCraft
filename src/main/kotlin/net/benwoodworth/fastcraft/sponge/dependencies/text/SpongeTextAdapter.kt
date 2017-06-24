package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.text.Text as SpongeText

/**
 * Adapts Sponge text.
 */
class SpongeTextAdapter(
        baseText: SpongeText
) : Text, Adapter<SpongeText>(baseText) {

    override fun equals(other: Any?): Boolean {
        return base == (other as? SpongeTextAdapter)?.base
    }

    override fun hashCode() = base.hashCode()
}
