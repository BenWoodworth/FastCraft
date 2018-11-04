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

    override fun color(color: FcTextColor) = also {
        this.color = color
    }

    override fun color(color: FcTextColors.() -> FcTextColor) = also {
        this.color(color(Bukkit_11300R01_FcTextColors))
    }

    override fun bold(bold: Boolean) = also {
        this.bold = bold
    }

    override fun italic(italic: Boolean) = also {
        this.italic = italic
    }

    override fun underline(underline: Boolean) = also {
        this.underline = underline
    }

    override fun strikethrough(strikethrough: Boolean) = also {
        this.strikethrough = strikethrough
    }

    override fun obfuscate(obfuscate: Boolean) = also {
        this.obfuscate = obfuscate
    }

    override fun extra(text: FcText) = also {
        extra.add(text)
    }

    override fun extra(text: (textBuilder: FcTextBuilder) -> FcText) = also {
        extra(text(Bukkit_11300R01_FcTextBuilder))
    }

    override fun build() = Bukkit_11300R01_FcTextString(
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
