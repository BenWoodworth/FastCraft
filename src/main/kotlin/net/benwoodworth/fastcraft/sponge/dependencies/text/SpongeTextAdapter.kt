package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.text.Text as SpongeText
import org.spongepowered.api.text.format.TextColors as SpongeTextColors

/**
 * Created by ben on 6/17/17.
 */
class SpongeTextAdapter(
        baseText: SpongeText
) : Text, Adapter<SpongeText>(baseText)
