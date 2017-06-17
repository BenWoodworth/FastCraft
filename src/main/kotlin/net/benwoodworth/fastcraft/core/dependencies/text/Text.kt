package net.benwoodworth.fastcraft.core.dependencies.text

/**
 * A Minecraft text component.
 */
interface Text {

    /** The text. */
    var text: String

    /** Additional text. */
    var extra: Array<Text>

    /** The text color. */
    var color: TextColor?

    /** Whether the text is bold. */
    var bold: Boolean

    /** Whether the text is italicized. */
    var italic: Boolean

    /** Whether the text is underlined. */
    var underlined: Boolean

    /** Whether the text is striked through. */
    var strikeThrough: Boolean

    /** Whether the text is obfuscated. */
    var obfuscated: Boolean
}
