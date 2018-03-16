package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import javax.inject.Inject
import javax.inject.Singleton

private const val ESC = '\u00A7'
private const val RESET = 'r'
private const val BOLD = 'l'
private const val ITALIC = 'o'
private const val UNDERLINED = 'n'
private const val STRIKE_THROUGH = 'm'
private const val OBFUSCATED = 'k'

@Singleton
class BukkitFcTextLegacyFormatter @Inject constructor(
) {

    fun format(text: BukkitFcText.Legacy): String {
        val state = FormatterState()
        appendFormat(text, state)
        return state.stringBuilder.toString()
    }

    private fun appendFormat(text: BukkitFcText.Legacy, details: FormatterState) {
        val newFormat = Format(text)

        details.apply {
            newFormat.append(stringBuilder, prevFormat)
            stringBuilder.append(text.text)
            prevFormat = newFormat
        }

        text.extra.forEach {
            appendFormat(it as BukkitFcText.Legacy, details)
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

        constructor(text: BukkitFcText.Legacy) : this(
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

        private fun append(stringBuilder: StringBuilder) {
            stringBuilder.apply {
                if (color != null) appendEsc(color)
                if (bold == true) appendEsc(BOLD)
                if (italic == true) appendEsc(ITALIC)
                if (underlined == true) appendEsc(UNDERLINED)
                if (strikeThrough == true) appendEsc(STRIKE_THROUGH)
                if (obfuscated == true) appendEsc(OBFUSCATED)
            }
        }

        fun append(stringBuilder: StringBuilder, previous: Format) {
            if (color == RESET) return append(stringBuilder)

            if ( // If formatting needs a reset
                    (bold == false && previous.bold != false) ||
                    (italic == false && previous.italic != false) ||
                    (underlined == false && previous.underlined != false) ||
                    (strikeThrough == false && previous.strikeThrough != false) ||
                    (obfuscated == false && previous.obfuscated != false)
            ) {
                stringBuilder.appendEsc(RESET)
                return append(stringBuilder)
            }

            stringBuilder.apply {
                color?.takeIf { it != previous.color }?.let { appendEsc(color) }
                bold?.takeIf { it != previous.bold }?.let { appendEsc(BOLD) }
                italic?.takeIf { it != previous.italic }?.let { appendEsc(ITALIC) }
                underlined?.takeIf { it != previous.underlined }?.let { appendEsc(UNDERLINED) }
                strikeThrough?.takeIf { it != previous.strikeThrough }?.let { appendEsc(STRIKE_THROUGH) }
                obfuscated?.takeIf { it != previous.obfuscated }?.let { appendEsc(OBFUSCATED) }
            }
        }
    }
}