package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.Text

/**
 * Adapts Sponge text.
 */
class SpongeFcText(
        override val base: Text
) : FcText, Adapter<Text>()
