package net.benwoodworth.fastcraft.dependencies.text

/**
 * Builds an instance of [Text].
 */
interface TextBuilder {

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
    fun text(text: String): TextBuilder

    /**
     * Set the extra text.
     *
     * @return fluent interface
     */
    fun addExtra(vararg extra: Text): TextBuilder

    /**
     * Set the text style.
     *
     * @return fluent interface
     */
    fun textStyle(style: TextStyle): TextBuilder
}