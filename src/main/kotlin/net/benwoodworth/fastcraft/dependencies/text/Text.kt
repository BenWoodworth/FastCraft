package net.benwoodworth.fastcraft.dependencies.text

/**
 * A Minecraft text component.
 *
 * [http://minecraft.gamepedia.com/Commands#Raw_JSON_Text]
 */
interface Text {

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
