package net.benwoodworth.fastcraft.implementations.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextBuilder
import net.benwoodworth.fastcraft.dependencies.text.TextStyle
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.format.TextFormat
import javax.inject.Inject

/**
 * Adapts the Sponge LiteralText builder.
 */
class SpongeTextBuilder @Inject constructor(
) : TextBuilder, Adapter<LiteralText.Builder>(LiteralText.builder("")) {

    override fun build(): Text {
        return SpongeText(base.build())
    }

    override fun text(text: String) = also {
        base.content(text)
    }

    override fun addExtra(vararg extra: Text) = also {
        base.append(extra.map {
            (it as SpongeText).base
        })
    }

    override fun textStyle(style: TextStyle) = also {
        base.format(TextFormat.of(
                (style.color as SpongeTextColor).base,
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