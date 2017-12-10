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
         * @return fluent interface
         */
        fun text(text: String): Builder

        /**
         * Set the extra text.
         *
         * @return fluent interface
         */
        fun addExtra(vararg extra: Text): Builder

        /**
         * Set the text style.
         *
         * @return fluent interface
         */
        fun textStyle(style: TextStyle): Builder
    }
}
