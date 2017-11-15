package net.benwoodworth.fastcraft.dependencies.abstractions.text

/**
 * Text color & formatting.
 */
class TextStyle(
        val color: TextColor,
        val bold: Boolean = false,
        val italic: Boolean = false,
        val underlined: Boolean = false,
        val strikeThrough: Boolean = false,
        val obfuscated: Boolean = false
)
