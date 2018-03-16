package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import javax.inject.Singleton

private const val ESC = '\u00A7'
private const val RESET = 'r'
private const val BOLD = 'l'
private const val ITALIC = 'o'
private const val UNDERLINED = 'n'
private const val STRIKE_THROUGH = 'm'
private const val OBFUSCATED = 'k'

@Singleton
class BukkitFcTextLegacyFormatter {

    fun format(text: BukkitFcText_Bukkit): String {
        val state = FormatterState()
        appendFormat(text, state)
        return state.stringBuilder.toString()
    }

    private fun appendFormat(text: BukkitFcText_Bukkit, details: FormatterState) {
        val newFormat = Format(text)
        details.stringBuilder
                .append(newFormat.toString(details.prevFormat))
                .append(text.text)

        details.prevFormat = newFormat

        for (extra in text.extra) {
            appendFormat(extra as BukkitFcText_Bukkit, details)
        }
    }

    private class FormatterState(
            val stringBuilder: StringBuilder = StringBuilder(),
            var prevFormat: Format = Format()
    )

    private class Format(
            val color: Char? = null,
            val bold: Boolean? = null,
            val italic: Boolean? = null,
            val underlined: Boolean? = null,
            val strikeThrough: Boolean? = null,
            val obfuscated: Boolean? = null
    ) {

        constructor(text: BukkitFcText_Bukkit) : this(
                text.color?.legacyCode,
                text.bold,
                text.italic,
                text.underlined,
                text.strikeThrough,
                text.obfuscated
        )

        @Suppress("NOTHING_TO_INLINE")
        private inline fun StringBuilder.appendEsc(code: Char) {
            append(ESC)
            append(code)
        }

        override fun toString(): String {
            return StringBuilder(12).apply {
                if (color != null) appendEsc(color)
                if (bold == true) appendEsc(BOLD)
                if (italic == true) appendEsc(ITALIC)
                if (underlined == true) appendEsc(UNDERLINED)
                if (strikeThrough == true) appendEsc(STRIKE_THROUGH)
                if (obfuscated == true) appendEsc(OBFUSCATED)
            }.toString()
        }

        fun toString(previous: Format): String {
            if (color == RESET) return toString()

            if ( // If formatting needs a reset
                    (bold == false && previous.bold != false) ||
                    (italic == false && previous.italic != false) ||
                    (underlined == false && previous.underlined != false) ||
                    (strikeThrough == false && previous.strikeThrough != false) ||
                    (obfuscated == false && previous.obfuscated != false)
            ) {
                return "$ESC$RESET${toString()}"
            }

            return StringBuilder(12).apply {
                color?.takeIf { it != previous.color }?.let { appendEsc(color) }
                bold?.takeIf { it != previous.bold }?.let { appendEsc(BOLD) }
                italic?.takeIf { it != previous.italic }?.let { appendEsc(ITALIC) }
                underlined?.takeIf { it != previous.underlined }?.let { appendEsc(UNDERLINED) }
                strikeThrough?.takeIf { it != previous.strikeThrough }?.let { appendEsc(STRIKE_THROUGH) }
                obfuscated?.takeIf { it != previous.obfuscated }?.let { appendEsc(OBFUSCATED) }
            }.toString()
        }
    }
}