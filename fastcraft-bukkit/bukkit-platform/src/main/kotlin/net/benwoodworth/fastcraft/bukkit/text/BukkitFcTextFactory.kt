package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

interface BukkitFcTextFactory : FcTextFactory {
    fun createFcTextTranslate(
        translate: String,
        color: FcTextColor? = null,
        bold: Boolean? = null,
        italic: Boolean? = null,
        underline: Boolean? = null,
        strikethrough: Boolean? = null,
        obfuscate: Boolean? = null,
        extra: List<FcText> = emptyList()
    ): FcText
}

fun FcTextFactory.createFcTextTranslate(
    translate: String,
    color: FcTextColor? = null,
    bold: Boolean? = null,
    italic: Boolean? = null,
    underline: Boolean? = null,
    strikethrough: Boolean? = null,
    obfuscate: Boolean? = null,
    extra: List<FcText> = emptyList()
): FcText {
    return (this as BukkitFcTextFactory).createFcTextTranslate(
        translate, color, bold, italic, underline, strikethrough, obfuscate, extra
    )
}
