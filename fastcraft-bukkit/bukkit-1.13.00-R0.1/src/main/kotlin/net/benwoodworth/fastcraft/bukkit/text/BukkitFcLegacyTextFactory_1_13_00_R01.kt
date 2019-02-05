package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import javax.inject.Inject

class BukkitFcLegacyTextFactory_1_13_00_R01 @Inject constructor(
    private val textColors: FcTextColors
) : BukkitFcLegacyTextFactory {

    override fun FcLegacyText(legacyText: String): FcLegacyText {
        return BukkitFcLegacyText_1_13_00_R01(legacyText)
    }

    override fun FcLegacyText(text: FcText, locale: FcLocale): FcLegacyText {
        val stringBuilder = StringBuilder()
        stringBuilder.appendFcText(text, locale)
        return BukkitFcLegacyText_1_13_00_R01(stringBuilder.toString())
    }

    private fun StringBuilder.appendFcText(
        fcText: FcText,
        locale: FcLocale,
        parentColor: FcTextColor = textColors.default,
        parentBold: Boolean = false,
        parentItalic: Boolean = false,
        parentUnderline: Boolean = false,
        parentStrikethrough: Boolean = false,
        parentObfuscate: Boolean = false
    ) {
        val color = fcText.bukkit.color ?: parentColor
        append('§')
        append(color.bukkit.legacyCode)

        val bold = fcText.bukkit.bold ?: parentBold
        val italic = fcText.bukkit.italic ?: parentItalic
        val underline = fcText.bukkit.underline ?: parentUnderline
        val strikethrough = fcText.bukkit.strikethrough ?: parentStrikethrough
        val obfuscate = fcText.bukkit.obfuscate ?: parentObfuscate

        if (bold) append("§l")
        if (italic) append("§o")
        if (underline) append("§n")
        if (strikethrough) append("§m")
        if (obfuscate) append("§k")

        val text = fcText.bukkit.text
        val translate = fcText.bukkit.translate

        when {
            text != null -> append(text)
            translate != null -> append(translate.bukkit.translate(locale))
        }

        fcText.bukkit.extra?.forEach { extra ->
            appendFcText(
                fcText = extra,
                locale = locale,
                parentColor = color,
                parentBold = bold,
                parentItalic = italic,
                parentUnderline = underline,
                parentStrikethrough = strikethrough,
                parentObfuscate = obfuscate
            )
        }
    }
}