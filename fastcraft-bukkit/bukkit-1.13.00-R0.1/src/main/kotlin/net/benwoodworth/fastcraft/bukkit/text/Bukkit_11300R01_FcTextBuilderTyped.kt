package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.*

@Suppress("ClassName")
class Bukkit_11300R01_FcTextBuilderTyped(
    private val text: String
) : FcTextBuilderTyped {

    private var color: FcTextColor? = null

    private var bold: Boolean? = null
    private var italic: Boolean? = null
    private var underline: Boolean? = null
    private var strikethrough: Boolean? = null
    private var obfuscate: Boolean? = null

    private val extra = mutableListOf<FcText>()

    override fun color(color: FcTextColor): FcTextBuilderTyped {
        this.color = color
        return this
    }

    override fun color(color: FcTextColors.() -> FcTextColor): FcTextBuilderTyped {
        this.color(color(Bukkit_11300R01_FcTextColors))
        return this
    }

    override fun bold(bold: Boolean): FcTextBuilderTyped {
        this.bold = bold
        return this
    }

    override fun italic(italic: Boolean): FcTextBuilderTyped {
        this.italic = italic
        return this
    }

    override fun underline(underline: Boolean): FcTextBuilderTyped {
        this.underline = underline
        return this
    }

    override fun strikethrough(strikethrough: Boolean): FcTextBuilderTyped {
        this.strikethrough = strikethrough
        return this
    }

    override fun obfuscate(obfuscate: Boolean): FcTextBuilderTyped {
        this.obfuscate = obfuscate
        return this
    }

    override fun extra(text: FcText): FcTextBuilderTyped {
        extra.add(text)
        return this
    }

    override fun extra(text: (textBuilder: FcTextBuilder) -> FcText): FcTextBuilderTyped {
        extra(text(Bukkit_11300R01_FcTextBuilder))
        return this
    }

    override fun build(): Bukkit_11300R01_FcTextString {
        return Bukkit_11300R01_FcTextString(
            text = text,
            color = color,
            bold = bold,
            italic = italic,
            underline = underline,
            strikethrough = strikethrough,
            obfuscate = obfuscate,
            extra = extra
        )
    }
}
