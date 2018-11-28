package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.json
import net.benwoodworth.fastcraft.bukkit.locale.Bukkit_11300R01_FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.util.getAs

@Suppress("ClassName")
class Bukkit_11300R01_FcText(
    builder: Bukkit_11300R01_FcTextBuilder,
    private val textColors: FcTextColors
) : FcText {

    val text: String? = builder.text
    val translate: FcTranslatable? = builder.translate

    val color: FcTextColor? = builder.color
    val bold: Boolean? = builder.bold
    val italic: Boolean? = builder.italic
    val underline: Boolean? = builder.underline
    val strikethrough: Boolean? = builder.strikethrough
    val obfuscate: Boolean? = builder.obfuscate

    val extra: List<FcText> = builder.extra.toList()

    fun getTextPart(locale: Bukkit_11300R01_FcLocale): String {
        return text
            ?: translate?.let { "[${locale.localeId}/$it]" } // TODO Translate
            ?: ""
    }

    fun toRaw(): String {
        return json {
            text?.let { "text" to it }
            translate?.let { "translate" to it }

            color
                ?.getAs<Bukkit_11300R01_FcTextColor>()
                ?.let { "color" to it.id }

            bold?.let { "bold" to it }
            italic?.let { "italic" to it }
            underline?.let { "underline" to it }
            strikethrough?.let { "strikethrough" to it }
            obfuscate?.let { "obfuscate" to it }

            extra
                .takeIf { it.any() }
                ?.map { it.getAs<Bukkit_11300R01_FcText>() }
                ?.let { "extra" to it }
        }.toString()
    }

    fun toLegacy(locale: FcLocale): String {
        return StringBuilder()
            .appendLegacyText(locale, this, LegacyFormatting())
            .toString()
    }

    private fun StringBuilder.appendLegacyText(
        locale: FcLocale,
        text: Bukkit_11300R01_FcText,
        parentFormatting: LegacyFormatting
    ): StringBuilder {
        val formatting = parentFormatting.inherit(text)

        append(formatting.color.code)

        if (formatting.bold) append("§l")
        if (formatting.italic) append("§o")
        if (formatting.underline) append("§n")
        if (formatting.strikethrough) append("§m")
        if (formatting.obfuscate) append("§k")

        append(text.getTextPart(locale.getAs()))

        text.extra.forEach {
            appendLegacyText(locale, it.getAs(), formatting)
        }

        return this
    }

    private inner class LegacyFormatting(
        val color: Bukkit_11300R01_FcTextColor = textColors.default.getAs(),
        val bold: Boolean = false,
        val italic: Boolean = false,
        val underline: Boolean = false,
        val strikethrough: Boolean = false,
        val obfuscate: Boolean = false
    ) {
        fun inherit(text: Bukkit_11300R01_FcText) = LegacyFormatting(
            text.color?.getAs() ?: color,
            text.bold ?: bold,
            text.italic ?: italic,
            text.underline ?: underline,
            text.strikethrough ?: strikethrough,
            text.obfuscate ?: obfuscate
        )
    }
}
