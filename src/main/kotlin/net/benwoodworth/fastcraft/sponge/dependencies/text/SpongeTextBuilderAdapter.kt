package net.benwoodworth.fastcraft.sponge.dependencies.text

import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.text.TextBuilder
import net.benwoodworth.fastcraft.core.dependencies.text.TextColor
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.text.LiteralText as SpongeLiteralText

/**
 * Adapts the Sponge LiteralText builder.
 */
class SpongeTextBuilderAdapter(
        baseBuilder: SpongeLiteralText.Builder
) : TextBuilder, Adapter<SpongeLiteralText.Builder>(baseBuilder) {

    override fun build(): Text {
        return SpongeTextAdapter(base.build())
    }

    override fun text(text: String): TextBuilder {
        base.content(text)
        return this
    }

    override fun addExtra(vararg extra: Text): TextBuilder {
        base.append(extra.map {
            (it as SpongeTextAdapter).base
        })
        return this
    }

    override fun color(color: TextColor?): TextBuilder {
        base.color((color as SpongeTextColorAdapter).base)
        return this
    }

    override fun bold(bold: Boolean): TextBuilder {
        base.style.bold(bold)
        return this
    }

    override fun italic(italic: Boolean): TextBuilder {
        base.style.italic(italic)
        return this
    }

    override fun underlined(underlined: Boolean): TextBuilder {
        base.style.underline(underlined)
        return this
    }

    override fun strikeThrough(strikeThrough: Boolean): TextBuilder {
        base.style.strikethrough(strikeThrough)
        return this
    }

    override fun obfuscated(obfuscated: Boolean): TextBuilder {
        base.style.obfuscated(obfuscated)
        return this
    }

    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hashCode(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
