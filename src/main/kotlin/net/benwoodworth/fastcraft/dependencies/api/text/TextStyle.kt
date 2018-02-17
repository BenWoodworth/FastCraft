package net.benwoodworth.fastcraft.dependencies.api.text

/**
 * Text color & formatting.
 */
data class TextStyle(
        val color: TextColor? = null,
        val bold: Boolean = false,
        val italic: Boolean = false,
        val underlined: Boolean = false,
        val strikeThrough: Boolean = false,
        val obfuscated: Boolean = false
)
