package net.benwoodworth.fastcraft.core.dependencies.text

/**
 * A factory to create [TextColor]s.
 */
interface TextColorRegistry {

    /** Leaves the color unset. */
    val none: TextColor

    /** Resets the color. Does not reset formatting. */
    val reset: TextColor

    /** Get the text color black. */
    val black: TextColor

    /** Get the text color dark blue. */
    val darkBlue: TextColor

    /** Get the text color dark green. */
    val darkGreen: TextColor

    /** Get the text color dark aqua. */
    val darkAqua: TextColor

    /** Get the text color dark red. */
    val darkRed: TextColor

    /** Get the text color dark purple. */
    val darkPurple: TextColor

    /** Get the text color gold. */
    val gold: TextColor

    /** Get the text color gray. */
    val gray: TextColor

    /** Get the text color dark gray. */
    val darkGray: TextColor

    /** Get the text color blue. */
    val blue: TextColor

    /** Get the text color green. */
    val green: TextColor

    /** Get the text color aqua. */
    val aqua: TextColor

    /** Get the text color red. */
    val red: TextColor

    /** Get the text color light purple. */
    val lightPurple: TextColor

    /** Get the text color yellow. */
    val yellow: TextColor

    /** Get the text color white. */
    val white: TextColor
}
