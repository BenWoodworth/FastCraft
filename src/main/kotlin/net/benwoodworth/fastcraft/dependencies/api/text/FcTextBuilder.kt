package net.benwoodworth.fastcraft.dependencies.api.text

/**
 * Builds an instance of [FcText].
 */
interface FcTextBuilder {

    /**
     * Build the text.
     *
     * @return the built text.
     */
    fun build(): FcText

    /**
     * Set the text.
     *
     * @return fluent interface.
     */
    fun text(text: String): FcTextBuilder

    /**
     * Set the extra text.
     *
     * @return fluent interface.
     */
    fun addExtra(vararg extra: FcText): FcTextBuilder

    /**
     * Set the text style.
     *
     * @return fluent interface.
     */
    fun textStyle(style: FcTextStyle): FcTextBuilder
}