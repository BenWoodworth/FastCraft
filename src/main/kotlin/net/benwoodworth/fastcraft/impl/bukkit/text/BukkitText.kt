package net.benwoodworth.fastcraft.impl.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextColor

/**
 * Bukkit implementation of [Text].
 */
class BukkitText(
        override val text: String,
        override val extra: List<Text>,
        override val color: TextColor?,
        override val bold: Boolean?,
        override val italic: Boolean?,
        override val underlined: Boolean?,
        override val strikeThrough: Boolean?,
        override val obfuscate: Boolean?
) : Text {

    /**
     * The formatted text String.
     */
    val formattedText: String = StringBuilder()
            .also { getText(null, it) }
            .toString()

    private fun getText(parentText: Text?, stringBuilder: StringBuilder) {
        stringBuilder.append("\u00A7r") // Reset

        (color ?: parentText?.color)
                ?.let { it as BukkitTextColor }
                ?.let { stringBuilder.append(it.code) }

        (obfuscate ?: parentText?.obfuscate)
                ?.takeIf { it }
                ?.let { stringBuilder.append("\u00A7k") }

        (bold ?: parentText?.bold)
                ?.takeIf { it }
                ?.let { stringBuilder.append("\u00A7l") }

        (italic ?: parentText?.italic)
                ?.takeIf { it }
                ?.let { stringBuilder.append("\u00A7m") }

        (underlined ?: parentText?.underlined)
                ?.takeIf { it }
                ?.let { stringBuilder.append("\u00A7n") }

        (strikeThrough ?: parentText?.strikeThrough)
                ?.takeIf { it }
                ?.let { stringBuilder.append("\u00A7o") }

        extra.forEach {
            it as BukkitText
            stringBuilder.append(it.getText(this, stringBuilder))
        }
    }

    /**
     * Bukkit implementation of [Text.Builder].
     */
    class Builder : Text.Builder {

        private var text: String = ""
        private var extra = mutableListOf<Text>()
        private var color: TextColor? = null
        private var bold: Boolean? = null
        private var italic: Boolean? = null
        private var underlined: Boolean? = null
        private var strikeThrough: Boolean? = null
        private var obfuscated: Boolean? = null

        override fun build() = BukkitText(
                text = text,
                extra = extra,
                color = color,
                bold = bold,
                italic = italic,
                underlined = underlined,
                strikeThrough = strikeThrough,
                obfuscate = obfuscated
        )

        override fun text(text: String) = also { this.text = text }
        override fun addExtra(vararg extra: Text) = also { this.extra.addAll(extra) }
        override fun color(color: TextColor) = also { this.color = color }
        override fun bold(bold: Boolean) = also { this.bold = bold }
        override fun italic(italic: Boolean) = also { this.italic = italic }
        override fun underlined(underlined: Boolean) = also { this.underlined = underlined }
        override fun strikeThrough(strikeThrough: Boolean) = also { this.strikeThrough = strikeThrough }
        override fun obfuscated(obfuscated: Boolean) = also { this.obfuscated = obfuscated }
    }
}
