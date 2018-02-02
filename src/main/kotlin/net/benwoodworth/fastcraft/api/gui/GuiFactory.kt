package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A factory for creating [Gui]'s.
 */
interface GuiFactory {

    /**
     * Create a GUI with a specific layout size.
     *
     * Typically only supports the sizes 9xN, 5x1, and 3x3.
     *
     * @param width the width of the [Gui]'s layout
     * @param height the height of the [Gui]'s layout
     * @param title the [Gui]'s title
     */
    fun sized(width: Int, height: Int, title: Text): Gui = when {
        width == 9 -> chest(height, title)
        width == 3 && height == 3 -> dispenser(title)
        width == 5 && height == 1 -> hopper(title)
        else -> throw IllegalArgumentException(
                "Unsupported GUI size: ${width}x$height"
        )
    }

    fun chest(height: Int, title: Text? = null): Gui.Chest

    fun dispenser(title: Text? = null): Gui.Dispenser

    fun hopper(title: Text? = null): Gui.Hopper
}