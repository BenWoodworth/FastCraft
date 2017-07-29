package net.benwoodworth.fastcraft.impl.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextStyle

/**
 * Bukkit implementation of [Text].
 */
class BukkitText(
        /**
         * The formatted text.
         */
        val text: String
) : Text {

    /**
     * Bukkit implementation of [Text.Builder].
     */
    class Builder : Text.Builder {

        private var text: String = ""
        private var extra = mutableListOf<Text>()
        private var style = TextStyle(
                BukkitTextColor('r') // Reset
        )

        override fun build(): Text {
            val stringBuilder = StringBuilder().apply {
                append((style.color as BukkitTextColor).code)

                takeIf { style.obfuscated }?.append("\u00A7k")
                takeIf { style.bold }?.append("\u00A7l")
                takeIf { style.strikeThrough }?.append("\u00A7m")
                takeIf { style.underlined }?.append("\u00A7n")
                takeIf { style.italic }?.append("\u00A7o")

                append(text)

                extra.forEach {
                    append((it as BukkitText).text)
                }
            }

            return BukkitText(stringBuilder.toString())
        }

        override fun text(text: String) = also { this.text = text }
        override fun addExtra(vararg extra: Text) = also { this.extra.addAll(extra) }
        override fun textStyle(style: TextStyle) = also { this.style = style }
    }
}
