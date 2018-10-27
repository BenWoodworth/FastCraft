package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextBuilderTyped : Extensible {

    fun color(color: FcTextColor): FcTextBuilderTyped
    fun color(color: FcTextColors.() -> FcTextColor): FcTextBuilderTyped

    fun bold(bold: Boolean = true): FcTextBuilderTyped
    fun italic(italic: Boolean = true): FcTextBuilderTyped
    fun underline(underline: Boolean = true): FcTextBuilderTyped
    fun strikethrough(strikethrough: Boolean = true): FcTextBuilderTyped
    fun obfuscate(obfuscate: Boolean = true): FcTextBuilderTyped

    fun extra(text: FcText): FcTextBuilderTyped
    fun extra(text: (textBuilder: FcTextBuilder) -> FcText): FcTextBuilderTyped

    fun build(): FcText
}
