package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor.Companion.chatColor
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor.Companion.id
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.JsonStringBuilder
import org.bukkit.ChatColor
import java.util.*
import javax.inject.Inject

class BukkitFcTextConverter_1_7_5_R01 @Inject constructor(
    private val localizer: BukkitLocalizer,
) : BukkitFcTextConverter {
    override fun toRaw(text: FcText): String {
        return JsonStringBuilder()
            .appendFcText(text)
            .toString()
    }

    private fun JsonStringBuilder.appendFcText(text: FcText): JsonStringBuilder {
        text as BukkitFcText
        return when (text) {
            is BukkitFcText.Legacy -> appendFcText(text)
            is BukkitFcText.Component -> appendFcText(text)
        }
    }

    private fun JsonStringBuilder.appendFcText(text: BukkitFcText.Legacy): JsonStringBuilder {
        return appendString(text.legacyText)
    }

    private fun JsonStringBuilder.appendFcText(text: BukkitFcText.Component): JsonStringBuilder {
        appendObject {
            when (text) {
                is BukkitFcText.Component.Text ->
                    appendElement("text") { appendString(text.text) }

                is BukkitFcText.Component.Translate ->
                    appendElement("translate") { appendString(text.translate) }
            }

            with(text) {
                color?.let {
                    appendElement("color") { appendString(it.id) }
                }

                bold?.let {
                    appendElement("bold") { appendBoolean(it) }
                }
                italic?.let {
                    appendElement("italic") { appendBoolean(it) }
                }
                underline?.let {
                    appendElement("underline") { appendBoolean(it) }
                }
                strikethrough?.let {
                    appendElement("strikethrough") { appendBoolean(it) }
                }
                obfuscate?.let {
                    appendElement("obfuscate") { appendBoolean(it) }
                }

                if (extra.any()) {
                    appendElement("extra") {
                        appendArray {
                            extra.forEach { appendFcText(it) }
                        }
                    }
                }
            }
        }

        return this
    }

    override fun toLegacy(text: FcText, locale: Locale): String {
        text as BukkitFcText
        return when (text) {
            is BukkitFcText.Legacy ->
                text.legacyText
            is BukkitFcText.Component ->
                LegacyTextBuilder(locale)
                    .appendText(text)
                    .toString()
        }
    }

    private data class LegacyFormat(
        var color: ChatColor = ChatColor.RESET,
        var bold: Boolean = false,
        var italic: Boolean = false,
        var underline: Boolean = false,
        var strikethrough: Boolean = false,
        var obfuscate: Boolean = false,
    )

    private inner class LegacyTextBuilder(
        private val locale: Locale,
    ) {
        private val stringBuilder = StringBuilder().append(ChatColor.RESET)

        // Formatting that has been appended.
        val appliedFormat = LegacyFormat()

        // Formatting that will be appended the next time plaintext is appended.
        var pendingFormat = LegacyFormat()

        override fun toString(): String {
            return stringBuilder.toString()
        }

        fun appendText(text: BukkitFcText): LegacyTextBuilder {
            appendText(text, LegacyFormat())
            return this
        }

        private fun appendText(text: FcText, parentFormat: LegacyFormat) {
            text as BukkitFcText
            when (text) {
                is BukkitFcText.Component ->
                    appendTextComponent(text, parentFormat)

                is BukkitFcText.Legacy ->
                    appendLegacyText(text.legacyText)
            }
        }

        private fun appendTextComponent(text: BukkitFcText.Component, parentFormat: LegacyFormat) {
            // The text format, inheriting from parentFormat in place of nulls.
            val format = LegacyFormat(
                color = text.color?.chatColor ?: parentFormat.color,
                bold = text.bold ?: parentFormat.bold,
                italic = text.italic ?: parentFormat.italic,
                underline = text.underline ?: parentFormat.underline,
                strikethrough = text.strikethrough ?: parentFormat.strikethrough,
                obfuscate = text.obfuscate ?: parentFormat.obfuscate
            )

            // Get the legacy to append
            val legacyText = when (text) {
                is BukkitFcText.Component.Text ->
                    text.text

                is BukkitFcText.Component.Translate ->
                    localizer.localize(text.translate, locale) ?: text.translate
            }

            // Set the format, and append the text.
            legacyText.let {
                pendingFormat = format.copy()
                appendLegacyText(it)
            }

            // Append the extra text.
            text.extra.forEach {
                appendText(it, format)
            }
        }

        private fun appendLegacyText(text: String) {
            var i = -1
            while (++i < text.length) {
                if (text[i] == ChatColor.COLOR_CHAR) {
                    // Read the formatting code, and append it.
                    if (++i <= text.lastIndex) {
                        appendFormat(text[i])
                    }
                } else {
                    // Read plaintext until the next formatting code or the end.
                    val start = i
                    while (i <= text.lastIndex && text[i] != ChatColor.COLOR_CHAR) {
                        i++
                    }

                    // Append un-applied formatting codes and plaintext
                    applyFormat()
                    stringBuilder.append(text, start, i)
                }
            }
        }

        private fun appendFormat(char: Char) {
            when (val format = ChatColor.getByChar(char)) {
                null -> {
                }

                ChatColor.BOLD -> this.pendingFormat.bold = true
                ChatColor.ITALIC -> this.pendingFormat.italic = true
                ChatColor.UNDERLINE -> this.pendingFormat.underline = true
                ChatColor.STRIKETHROUGH -> this.pendingFormat.strikethrough = true
                ChatColor.MAGIC -> this.pendingFormat.obfuscate = true

                else -> this.pendingFormat.apply {
                    // Set color, and clear other formatting
                    color = format
                    bold = false
                    italic = false
                    underline = false
                    strikethrough = false
                    obfuscate = false
                }
            }
        }

        private fun applyFormat() {
            // If the color code resets, or if some formatting needs to be removed,
            // then add color formatting, since it resets formatting.
            if (
                appliedFormat.color != pendingFormat.color ||
                appliedFormat.bold && !pendingFormat.bold ||
                appliedFormat.italic && !pendingFormat.italic ||
                appliedFormat.underline && !pendingFormat.underline ||
                appliedFormat.strikethrough && !pendingFormat.strikethrough ||
                appliedFormat.obfuscate && !pendingFormat.obfuscate
            ) {
                stringBuilder.append(pendingFormat.color)

                appliedFormat.apply {
                    color = pendingFormat.color
                    bold = false
                    italic = false
                    underline = false
                    strikethrough = false
                    obfuscate = false
                }
            }

            // If bold format needs to be applied, then apply it.
            if (pendingFormat.bold && !appliedFormat.bold) {
                stringBuilder.append(ChatColor.BOLD)
                appliedFormat.bold = true
            }

            // If italic format needs to be applied, then apply it.
            if (pendingFormat.italic && !appliedFormat.italic) {
                stringBuilder.append(ChatColor.ITALIC)
                appliedFormat.italic = true
            }

            // If underline format needs to be applied, then apply it.
            if (pendingFormat.underline && !appliedFormat.underline) {
                stringBuilder.append(ChatColor.UNDERLINE)
                appliedFormat.underline = true
            }

            // If strikethrough format needs to be applied, then apply it.
            if (pendingFormat.strikethrough && !appliedFormat.strikethrough) {
                stringBuilder.append(ChatColor.STRIKETHROUGH)
                appliedFormat.strikethrough = true
            }

            // If obfuscate format needs to be applied, then apply it.
            if (pendingFormat.obfuscate && !appliedFormat.obfuscate) {
                stringBuilder.append(ChatColor.MAGIC)
                appliedFormat.obfuscate = true
            }
        }
    }

    override fun toPlaintext(text: FcText, locale: Locale): String {
        return buildString {
            fun appendLegacy(text: String) {
                var i = 0
                while (i < text.length) {
                    when (val ch = text[i]) {
                        '§' -> i += 2
                        else -> {
                            append(ch)
                            i++
                        }
                    }
                }
            }

            fun appendText(text: FcText) {
                text as BukkitFcText
                when (text) {
                    is BukkitFcText.Legacy -> appendLegacy(text.legacyText)
                    is BukkitFcText.Component -> {
                        when (text) {
                            is BukkitFcText.Component.Text -> appendLegacy(text.text)
                            is BukkitFcText.Component.Translate -> appendLegacy(
                                localizer.localize(text.translate, locale) ?: text.translate
                            )
                        }

                        text.extra.forEach { appendText(it) }
                    }
                }
            }

            appendText(text)
        }
    }
}
