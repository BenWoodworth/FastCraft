package net.benwoodworth.fastcraft.dependencies.api.text

/**
 * FcText color & formatting.
 */
data class FcTextStyle(
        val color: FcTextColor? = null,
        val bold: Boolean = false, //TODO Make optional?
        val italic: Boolean = false,
        val underlined: Boolean = false,
        val strikeThrough: Boolean = false,
        val obfuscated: Boolean = false
)
