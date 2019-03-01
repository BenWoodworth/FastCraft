package net.benwoodworth.fastcraft.platform.text

interface FcTextFactory {

    fun createFcText(
        text: String,
        color: FcTextColor? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underline: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscate: Boolean? = null,
        extra: List<FcText>? = null
    ): FcText
}