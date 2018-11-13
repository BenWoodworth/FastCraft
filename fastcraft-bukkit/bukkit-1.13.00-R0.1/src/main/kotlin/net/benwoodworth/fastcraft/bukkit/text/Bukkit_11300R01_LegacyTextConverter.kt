package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.server.FcLocale
import net.benwoodworth.fastcraft.util.getAs

internal object Bukkit_11300R01_LegacyTextConverter {

    fun convert(text: Bukkit_11300R01_FcText, locale: FcLocale): String {
        return StringBuilder()
            .appendLegacyText(text, Formatting())
            .toString()
    }

    private class Formatting(
        val color: Bukkit_11300R01_FcTextColor = Bukkit_11300R01_FcTextColors.default,
        val bold: Boolean = false,
        val italic: Boolean = false,
        val underline: Boolean = false,
        val strikethrough: Boolean = false,
        val obfuscate: Boolean = false
    ) {
        fun inherit(text: Bukkit_11300R01_FcText): Formatting {
            return Formatting(
                text.color?.getAs() ?: color,
                text.bold ?: bold,
                text.italic ?: italic,
                text.underline ?: underline,
                text.strikethrough ?: strikethrough,
                text.obfuscate ?: obfuscate
            )
        }
    }

    private fun StringBuilder.appendLegacyText(
        text: Bukkit_11300R01_FcText,
        parentFormatting: Formatting
    ): StringBuilder {
        val formatting = parentFormatting.inherit(text)

        append(formatting.color.code)

        if (formatting.bold) append("§l")
        if (formatting.italic) append("§o")
        if (formatting.underline) append("§n")
        if (formatting.strikethrough) append("§m")
        if (formatting.obfuscate) append("§k")

        append(text.getTextPart())

        text.extra.forEach {
            appendLegacyText(it.getAs(), formatting)
        }

        return this
    }
}