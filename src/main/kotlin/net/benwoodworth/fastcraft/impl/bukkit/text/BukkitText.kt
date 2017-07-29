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

    /*
     * Formatting code details:
     *
     * Reset: r
     * Colors: 0-9,a-f
     * Formatting:
     * - k: Obfuscate
     * - l: Bold
     * - m: Strikethrough
     * - n: Underline
     * - o: Italic
     *
     * Multiple formatting codes can be used
     * Color codes reset formatting
     * Reset resets color and formatting
     */

    /*
     * JSON text details:
     *
     * Extra: Inherits text color & formatting
     */

    /**
     * Generate the formatted text String.
     */
    fun getText(): String {
        return StringBuilder()
                .let { getText(null, it) }
                .toString()
    }

    private fun getText(parentText: Text?, stringBuilder: StringBuilder) {
        stringBuilder.append("\u00A7r")

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
            stringBuilder.append((it as BukkitText).getText(this, stringBuilder))
        }
    }

    /**
     * Bukkit implementation of [Text.Builder].
     */
    class Builder : Text.Builder {

        val formattingChars = mutableSetOf<Char>()

        val color: Char? = null

        override fun build(): Text {
            TODO("not implemented")
        }

        override fun text(text: String): Text.Builder {
            TODO("not implemented")
        }

        override fun addExtra(vararg extra: Text): Text.Builder {
            TODO("not implemented")
        }

        override fun color(color: TextColor?): Text.Builder {
            TODO("not implemented")
        }

        override fun bold(bold: Boolean): Text.Builder {
            TODO("not implemented")
        }

        override fun italic(italic: Boolean): Text.Builder {
            TODO("not implemented")
        }

        override fun underlined(underlined: Boolean): Text.Builder {
            TODO("not implemented")
        }

        override fun strikeThrough(strikeThrough: Boolean): Text.Builder {
            TODO("not implemented")
        }

        override fun obfuscated(obfuscated: Boolean): Text.Builder {
            TODO("not implemented")
        }
    }
}
