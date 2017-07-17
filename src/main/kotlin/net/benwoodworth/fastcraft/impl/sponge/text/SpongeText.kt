package net.benwoodworth.fastcraft.impl.sponge.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.Text as Sponge_Text

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

        override fun text(text: String): Text.Builder {
            base.content(text)
            return this
        }

        override fun addExtra(vararg extra: Text): Text.Builder {
            base.append(extra.map {
                (it as SpongeText).base
            })
            return this
        }

        override fun color(color: TextColor?): Text.Builder {
            base.color((color as SpongeTextColor).base)
            return this
        }

        override fun bold(bold: Boolean): Text.Builder {
            base.style.bold(bold)
            return this
        }

        override fun italic(italic: Boolean): Text.Builder {
            base.style.italic(italic)
            return this
        }

        override fun underlined(underlined: Boolean): Text.Builder {
            base.style.underline(underlined)
            return this
        }

        override fun strikeThrough(strikeThrough: Boolean): Text.Builder {
            base.style.strikethrough(strikeThrough)
            return this
        }

        override fun obfuscated(obfuscated: Boolean): Text.Builder {
            base.style.obfuscated(obfuscated)
            return this
        }
    }
}
