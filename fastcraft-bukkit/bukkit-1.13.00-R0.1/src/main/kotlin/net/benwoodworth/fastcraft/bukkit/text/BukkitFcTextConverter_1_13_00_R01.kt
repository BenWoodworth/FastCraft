package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.ChatColor
import javax.inject.Inject

class BukkitFcTextConverter_1_13_00_R01 @Inject constructor(
    private val localizer: BukkitLocalizer
) : BukkitFcTextConverter {

    override fun FcText.toRaw(): String {
        return RawText(this.bukkit).toString()
    }

    @Serializable
    private class RawText(
        @Optional var text: String? = null,
        @Optional var translate: String? = null,
        @Optional var color: String? = null,
        @Optional var bold: Boolean? = null,
        @Optional var italic: Boolean? = null,
        @Optional var underline: Boolean? = null,
        @Optional var strikethrough: Boolean? = null,
        @Optional var obfuscate: Boolean? = null,
        @Optional var extra: List<RawText>? = null
    ) {
        constructor(text: BukkitFcText) : this(
            text = text.text,
            translate = text.translate,
            color = text.color?.bukkit?.id,
            bold = text.bold,
            italic = text.italic,
            underline = text.underline,
            strikethrough = text.strikethrough,
            obfuscate = text.obfuscate,
            extra = text.extra
                .takeIf { it.any() }
                ?.map { RawText(it.bukkit) }
        )

        private companion object {
            val format = Json(encodeDefaults = false)
        }

        override fun toString(): String {
            return format.stringify(serializer(), this)
        }
    }

    override fun FcText.toLegacy(locale: FcLocale): String {
        bukkit.legacy?.let {
            return it
        }

        return LegacyTextBuilder(locale)
            .appendText(this.bukkit)
            .toString()
    }

    private data class LegacyFormat(
        var color: ChatColor = ChatColor.RESET,
        var bold: Boolean = false,
        var italic: Boolean = false,
        var underline: Boolean = false,
        var strikethrough: Boolean = false,
        var obfuscate: Boolean = false
    )

    private inner class LegacyTextBuilder(
        private val locale: FcLocale
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

        private fun appendText(text: BukkitFcText, parentFormat: LegacyFormat) {
            // The text format, inheriting from parentFormat in place of nulls.
            val format = LegacyFormat(
                color = text.color?.bukkit?.chatColor ?: parentFormat.color,
                bold = text.bold ?: parentFormat.bold,
                italic = text.italic ?: parentFormat.italic,
                underline = text.underline ?: parentFormat.underline,
                strikethrough = text.strikethrough ?: parentFormat.strikethrough,
                obfuscate = text.obfuscate ?: parentFormat.obfuscate
            )

            // Get the legacy to append
            val legacyText = text.text
                ?: text.translate?.let { localizer.localize(it, locale) ?: it }

            // Set the format, and append the text.
            legacyText?.let {
                pendingFormat = format.copy()
                appendLegacyText(it)
            }

            // Append the extra text.
            text.extra.forEach {
                appendText(it.bukkit, format)
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

                    // Append unapplied formatting codes and plaintext
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
}