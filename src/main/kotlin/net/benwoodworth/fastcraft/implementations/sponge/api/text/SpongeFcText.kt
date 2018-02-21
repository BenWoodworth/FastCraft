package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.Text as Sponge_Text
import org.spongepowered.api.text.format.TextStyle as Sponge_TextStyle

/**
 * Adapts Sponge text.
 */
class SpongeFcText(
        baseText: Sponge_Text
) : FcText, Adapter<Sponge_Text>(baseText)
