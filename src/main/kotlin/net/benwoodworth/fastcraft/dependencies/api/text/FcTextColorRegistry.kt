package net.benwoodworth.fastcraft.dependencies.api.text

/**
 * A factory to create [FcTextColor]s.
 */
interface FcTextColorRegistry {

    /**
     * Resets the color. Does not reset formatting.
     */
    val reset: FcTextColor

    /**
     * Get the text color black.
     */
    val black: FcTextColor

    /**
     * Get the text color dark blue.
     */
    val darkBlue: FcTextColor

    /**
     * Get the text color dark green.
     */
    val darkGreen: FcTextColor

    /**
     * Get the text color dark aqua.
     */
    val darkAqua: FcTextColor

    /**
     * Get the text color dark red.
     */
    val darkRed: FcTextColor

    /**
     * Get the text color dark purple.
     */
    val darkPurple: FcTextColor

    /**
     * Get the text color gold.
     */
    val gold: FcTextColor

    /**
     * Get the text color gray.
     */
    val gray: FcTextColor

    /**
     * Get the text color dark gray.
     */
    val darkGray: FcTextColor

    /**
     * Get the text color blue.
     */
    val blue: FcTextColor

    /**
     * Get the text color green.
     */
    val green: FcTextColor

    /**
     * Get the text color aqua.
     */
    val aqua: FcTextColor

    /**
     * Get the text color red.
     */
    val red: FcTextColor

    /**
     * Get the text color light purple.
     */
    val lightPurple: FcTextColor

    /**
     * Get the text color yellow.
     */
    val yellow: FcTextColor

    /**
     * Get the text color white.
     */
    val white: FcTextColor
}
