package net.benwoodworth.fastcraft.implementations.bukkit.api.text.bukkit

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcText
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("ClassName")
interface BukkitFcText_Bukkit : BukkitFcText {

    val text: String

    var color: BukkitFcTextColor_Bukkit?
    var bold: Boolean?
    var italic: Boolean?
    var underlined: Boolean?
    var strikeThrough: Boolean?
    var obfuscated: Boolean?
    //var shiftClickAction: BukkitFcTextAction_Bukkit.ShiftClick?
    //var clickAction: BukkitFcTextAction_Bukkit.Click?
    //var hoverAction: BukkitFcTextAction_Bukkit.Hover?
    var extra: MutableList<BukkitFcText>

    interface Factory : BukkitFcText.Factory

    class Builder(
            private val baseText: BukkitFcText_Bukkit
    ) : BukkitFcText.Builder {

        override fun build() = baseText

        override fun setColor(color: FcTextColor) = also {
            baseText.color = color as BukkitFcTextColor_Bukkit
        }

        override fun setBold(bold: Boolean) = also {
            baseText.bold = bold
        }

        override fun setItalic(italic: Boolean) = also {
            baseText.italic = italic
        }

        override fun setUnderlined(underlined: Boolean) = also {
            baseText.underlined = underlined
        }

        override fun setStrikeThrough(strikeThrough: Boolean) = also {
            baseText.strikeThrough = strikeThrough
        }

        override fun setObfuscated(obfuscated: Boolean) = also {
            baseText.obfuscated = obfuscated
        }

        //override fun setShiftClickAction(action: FcTextAction.ShiftClick) = also {
        //    baseText.shiftClickAction = action as BukkitFcTextAction_Bukkit.ShiftClick
        //}

        //override fun setClickAction(action: FcTextAction.Click) = also {
        //    baseText.clickAction = action as BukkitFcTextAction_Bukkit.Click
        //}

        //override fun setHoverAction(action: FcTextAction.Hover) = also {
        //    baseText.hoverAction = action as BukkitFcTextAction_Bukkit.Hover
        //}

        override fun addExtra(text: FcText) = also {
            baseText.extra.add(text as BukkitFcText)
        }
    }

    @Singleton
    class Formatter @Inject constructor(
    ) {

        fun format(text: BukkitFcText_Bukkit): String {
            val state = FormatterState()
            appendFormat(text, state)
            return state.stringBuilder.toString()
        }

        private fun appendFormat(text: BukkitFcText_Bukkit, details: FormatterState) {
            val newFormat = Format(text)

            details.apply {
                newFormat.append(stringBuilder, prevFormat)
                stringBuilder.append(text.text)
                prevFormat = newFormat
            }

            text.extra.forEach {
                appendFormat(it as BukkitFcText_Bukkit, details)
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

            private companion object {
                private const val ESC = '\u00A7'
                private const val RESET = 'r'
                private const val BOLD = 'l'
                private const val ITALIC = 'o'
                private const val UNDERLINED = 'n'
                private const val STRIKE_THROUGH = 'm'
                private const val OBFUSCATED = 'k'
            }

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
}