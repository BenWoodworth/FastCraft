package net.benwoodworth.fastcraft.dependencies.text

/**
 * A Minecraft text component.
 *
 * [http://minecraft.gamepedia.com/Commands#Raw_JSON_Text]
 */
interface Text {

    /**
     * The text.
     */
    val text: String

    /**
     * Extra text.
     */
    val extra: List<Text>

    /**
     * The text color.
     */
    val color: TextColor?

    /**
     * Whether the text is bold, or `null` if unset.
     */
    val bold: Boolean?

    /**
     * Whether the text is italicized, or `null` if unset.
     */
    val italic: Boolean?

    /**
     * Whether the text is underlined, or `null` if unset.
     */
    val underlined: Boolean?

    /**
     * Whether the text has a strike through, or `null` if unset.
     */
    val strikeThrough: Boolean?

    /**
     * Whether the text is obfuscated, or `null` if unset.
     */
    val obfuscate: Boolean?

    /**
     * Builds an instance of [Text].
     */
    interface Builder {

        /**
         * Build the text.
         *
         * @return the built text
         */
        fun build(): Text

        /**
         * Set the text.
         *
         * @return fluid interface
         */
        fun text(text: String): Text.Builder

        /**
         * Set the extra text.
         *
         * @return fluid interface
         */
        fun addExtra(vararg extra: Text): Text.Builder

        /**
         * Set the text color.
         *
         * @return fluid interface
         */
        fun color(color: TextColor?): Text.Builder

        /**
         * Set whether the text is bold.
         *
         * @return fluid interface
         */
        fun bold(bold: Boolean): Text.Builder

        /**
         * Set whether the text is italic.
         *
         * @return fluid interface
         */
        fun italic(italic: Boolean): Text.Builder

        /**
         * Set whether the text is underlined.
         *
         * @return fluid interface
         */
        fun underlined(underlined: Boolean): Text.Builder

        /**
         * Set whether the text has a strike.
         *
         * @return fluid interface
         */
        fun strikeThrough(strikeThrough: Boolean): Text.Builder

        /**
         * Set whether the text is obfuscated.
         *
         * @return fluid interface
         */
        fun obfuscated(obfuscated: Boolean): Text.Builder
    }
}
