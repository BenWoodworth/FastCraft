package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.*
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors

@Suppress("ClassName")
class `BukkitFcTextBuilderTyped$1_13_R0_1`(
    private val text: String
) : net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextBuilderTyped {

    private var color: net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor? = null

    private var bold: Boolean? = null
    private var italic: Boolean? = null
    private var underline: Boolean? = null
    private var strikethrough: Boolean? = null
    private var obfuscate: Boolean? = null

    private val extra = mutableListOf<FcText>()

    override fun color(color: FcTextColor) = also {
        this.color = color as net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor
    }

    override fun color(color: FcTextColors.() -> FcTextColor) = also {
        this.color(color(`BukkitFcTextColors$1_13_R0_1`))
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
        extra(text(`BukkitFcTextBuilder$1_13_R0_1`))
    }

    override fun build() = `BukkitFcTextString$1_13_R0_1`(
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
