package net.benwoodworth.fastcraft.platform.api.gui

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A factory for creating [Gui]'s.
 */
interface GuiFactory {

    /**
     * Create a GUI with a specific layout size.
     *
     * Typically only supports the sizes 9xN, 5x1, and 3x3.
     * Unsupported sizes will default to 9xN.
     *
     * @param width the width of the [Gui]'s layout.
     * @param height the height of the [Gui]'s layout.
     * @param title the [Gui]'s title.
     */
    fun withSize(width: Int, height: Int, title: FcText): Gui = when {
        width == 3 && height == 3 -> dispenser(title)
        width == 5 && height == 1 -> hopper(title)
        else -> chest(height, title)
    }

    fun chest(height: Int, title: FcText? = null): Gui.Chest

    fun dispenser(title: FcText? = null): Gui.Dispenser

    fun hopper(title: FcText? = null): Gui.Hopper
}