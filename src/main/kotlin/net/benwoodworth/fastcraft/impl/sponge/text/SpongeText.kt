package net.benwoodworth.fastcraft.impl.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Adapts Sponge text.
 */
class SpongeText(
        baseText: Sponge_Text
) : Text, Adapter<Sponge_Text>(baseText)
