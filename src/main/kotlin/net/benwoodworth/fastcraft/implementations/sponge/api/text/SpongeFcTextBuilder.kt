package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextStyle
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.format.TextFormat
import javax.inject.Inject

/**
 * Adapts the Sponge LiteralText builder.
 */
class SpongeFcTextBuilder @Inject constructor(
) : FcTextBuilder, Adapter<LiteralText.Builder>() {

    override val base: LiteralText.Builder = LiteralText.builder("")
    
    override fun build(): FcText {
        return SpongeFcText(base.build())
    }

    override fun text(text: String) = also {
        base.content(text)
    }

    override fun addExtra(vararg extra: FcText) = also {
        base.append(extra.map {
            (it as SpongeFcText).base
        })
    }

    override fun textStyle(style: FcTextStyle) = also {
        base.format(TextFormat.of(
                (style.color as SpongeFcTextColor).base,
                org.spongepowered.api.text.format.TextStyle(
                        style.bold,
                        style.italic,
                        style.underlined,
                        style.strikeThrough,
                        style.obfuscated
                )
        ))
    }
}