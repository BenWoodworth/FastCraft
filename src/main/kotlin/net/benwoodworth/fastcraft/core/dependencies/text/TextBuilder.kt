package net.benwoodworth.fastcraft.core.dependencies.text

import net.benwoodworth.fastcraft.util.Builder

/**
 * Builds an instance of [Text].
 */
interface TextBuilder : Builder<Text> {

    /**
     * Set the text.
     *
     * @return fluid interface
     */
    fun text(text: String): TextBuilder

    /**
     * Set the extra text.
     *
     * @return fluid interface
     */
    fun addExtra(vararg extra: Text): TextBuilder

    /**
     * Set the text color.
     *
     * @return fluid interface
     */
    fun color(color: TextColor?): TextBuilder

    /**
     * Set whether the text is bold.
     *
     * @return fluid interface
     */
    fun bold(bold: Boolean): TextBuilder

    /**
     * Set whether the text is italic.
     *
     * @return fluid interface
     */
    fun italic(italic: Boolean): TextBuilder

    /**
     * Set whether the text is underlined.
     *
     * @return fluid interface
     */
    fun underlined(underlined: Boolean): TextBuilder

    /**
     * Set whether the text has a strike.
     *
     * @return fluid interface
     */
    fun strikeThrough(strikeThrough: Boolean): TextBuilder

    /**
     * Set whether the text is obfuscated.
     *
     * @return fluid interface
     */
    fun obfuscated(obfuscated: Boolean): TextBuilder
}
