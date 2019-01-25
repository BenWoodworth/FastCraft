package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

interface FcTextFactory {

    fun FcText(
        text: String,
        color: FcTextColor? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underline: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscate: Boolean? = null,
        extra: List<FcText>? = null
    ): FcText

    fun FcText(
        translate: FcTranslatable,
        color: FcTextColor? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underline: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscate: Boolean? = null,
        extra: List<FcText>? = null
    ): FcText
}