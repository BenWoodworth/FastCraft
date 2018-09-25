package net.benwoodworth.fastcraft.platform.bukkit.text

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.bukkit.text.bukkit.BukkitFcText_Bukkit_BuilderFactory
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcText_Bukkit(
        override val text: String,
        override val color: BukkitFcTextColor,
        override val bold: Boolean?,
        override val italic: Boolean?,
        override val underlined: Boolean?,
        override val strikeThrough: Boolean?,
        override val obfuscated: Boolean?,
        override val extra: List<BukkitFcText>
) : BukkitFcText {

    class Factory @Inject constructor(
            private val builderFactory: BukkitFcText_Bukkit_BuilderFactory
    ) : BukkitFcText.Factory {

        override fun buildText(text: String): FcText.Builder {
            return builderFactory.create(text)
        }
    }

    @AutoFactory
    class Builder(
            private val text: String,

            @Provided private val colors: BukkitFcTextColor.Catalog
    ) : BukkitFcText.Builder {

        private var color: BukkitFcTextColor = colors.none
        private var bold: Boolean? = null
        private var italic: Boolean? = null
        private var underlined: Boolean? = null
        private var strikeThrough: Boolean? = null
        private var obfuscated: Boolean? = null
        private var extra = mutableListOf<BukkitFcText>()

        override fun build() = BukkitFcText_Bukkit(
                text, color, bold, italic, underlined, strikeThrough, obfuscated, extra.toList()
        )

        override fun setColor(color: FcTextColor) = also { this.color = color as BukkitFcTextColor }
        override fun setBold(bold: Boolean) = also { this.bold = bold }
        override fun setItalic(italic: Boolean) = also { this.italic = italic }
        override fun setUnderlined(underlined: Boolean) = also { this.underlined = underlined }
        override fun setStrikeThrough(strikeThrough: Boolean) = also { this.strikeThrough = strikeThrough }
        override fun setObfuscated(obfuscated: Boolean) = also { this.obfuscated = obfuscated }
        override fun addExtra(text: FcText) = also { extra.add(text as BukkitFcText) }
    }

    class Converter @Inject constructor(
            private val colors: BukkitFcTextColor.Catalog
    ) : BukkitFcText.Converter {

        private companion object {
            const val BOLD = "\u00A7l"
            const val ITALIC = "\u00A7o"
            const val UNDERLINED = "\u00A7n"
            const val STRIKE_THROUGH = "\u00A7m"
            const val OBFUSCATED = "\u00A7k"
        }

        override fun toLegacyText(text: BukkitFcText): String {
            return buildLegacyText(text).toString()
        }

        private fun buildLegacyText(
                text: BukkitFcText,
                currentFormat: CurrentFormat = CurrentFormat(colors.none),
                stringBuilder: StringBuilder = StringBuilder()
        ): StringBuilder {

            val resetFormatting: Boolean = (text.color == colors.reset) ||
                    (text.bold == false && currentFormat.bold != false) ||
                    (text.italic == false && currentFormat.italic != false) ||
                    (text.underlined == false && currentFormat.underlined != false) ||
                    (text.strikeThrough == false && currentFormat.strikeThrough != false) ||
                    (text.obfuscated == false && currentFormat.obfuscated != false)

            if (resetFormatting) {
                stringBuilder.append(colors.reset.legacyCode)

                currentFormat.apply {
                    color = colors.reset
                    bold = false
                    italic = false
                    underlined = false
                    strikeThrough = false
                    obfuscated = false
                }
            }

            if (text.color != currentFormat.color) stringBuilder.append(text.color.legacyCode)
            if (text.bold == true && currentFormat.bold != true) stringBuilder.append(BOLD)
            if (text.italic == true && currentFormat.italic != true) stringBuilder.append(ITALIC)
            if (text.underlined == true && currentFormat.underlined != true) stringBuilder.append(UNDERLINED)
            if (text.strikeThrough == true && currentFormat.strikeThrough != true) stringBuilder.append(STRIKE_THROUGH)
            if (text.obfuscated == true && currentFormat.obfuscated != true) stringBuilder.append(OBFUSCATED)

            stringBuilder.append(text.text)

            text.extra.forEach { extra ->
                buildLegacyText(extra, currentFormat, stringBuilder)
            }

            return stringBuilder
        }

        private class CurrentFormat(
                var color: BukkitFcTextColor,
                var bold: Boolean? = null,
                var italic: Boolean? = null,
                var underlined: Boolean? = null,
                var strikeThrough: Boolean? = null,
                var obfuscated: Boolean? = null
        )
    }
}