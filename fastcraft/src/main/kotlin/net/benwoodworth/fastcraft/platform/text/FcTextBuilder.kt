package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextBuilder : Extensible {

    fun color(color: FcTextColor): FcTextBuilder
    fun color(color: FcTextColors.() -> FcTextColor): FcTextBuilder

    fun bold(bold: Boolean = true): FcTextBuilder
    fun italic(italic: Boolean = true): FcTextBuilder
    fun underline(underline: Boolean = true): FcTextBuilder
    fun strikethrough(strikethrough: Boolean = true): FcTextBuilder
    fun obfuscate(obfuscate: Boolean = true): FcTextBuilder

    fun extra(text: FcText): FcTextBuilder
    fun extra(text: (textBuilder: FcTextBuilder) -> FcText): FcTextBuilder

    fun build(): FcText
}
