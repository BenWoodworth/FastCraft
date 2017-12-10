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
        private var style: TextStyle? = null

        override fun build(): Text {
            val builder = StringBuilder()

            style?.run {
                builder.append((color as BukkitTextColor).code)

                builder.takeIf { obfuscated }?.append("\u00A7k")
                builder.takeIf { bold }?.append("\u00A7l")
                builder.takeIf { strikeThrough }?.append("\u00A7m")
                builder.takeIf { underlined }?.append("\u00A7n")
                builder.takeIf { italic }?.append("\u00A7o")
            }

            builder.append(text)

            extra.forEach {
                builder.append((it as BukkitText).text)
            }

            return BukkitText(builder.toString())
        }

        override fun text(text: String) = also { this.text = text }
        override fun addExtra(vararg extra: Text) = also { this.extra.addAll(extra) }
        override fun textStyle(style: TextStyle) = also { this.style = style }
    }
}
