package net.benwoodworth.fastcraft.impl.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextStyle
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.format.TextFormat
import org.spongepowered.api.text.Text as Sponge_Text
import org.spongepowered.api.text.format.TextStyle as Sponge_TextStyle

/**
 * Adapts Sponge text.
 */
class SpongeText(
        baseText: Sponge_Text
) : Text, Adapter<Sponge_Text>(baseText) {

    /**
     * Adapts the Sponge LiteralText builder.
     */
    class Builder(
            baseBuilder: LiteralText.Builder
    ) : Text.Builder, Adapter<LiteralText.Builder>(baseBuilder) {

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
                    Sponge_TextStyle(
                            style.bold,
                            style.italic,
                            style.underlined,
                            style.strikeThrough,
                            style.obfuscated
                    )
            ))
        }
    }
}
