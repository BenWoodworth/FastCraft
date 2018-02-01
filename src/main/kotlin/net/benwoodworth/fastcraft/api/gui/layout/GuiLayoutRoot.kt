package net.benwoodworth.fastcraft.api.gui.layout

/**
 * The top-level layout within a GUI.
 */
class GuiLayoutRoot(
        width: Int,
        height: Int
) : GuiLayoutAbstract(
        0,
        0,
        width,
        height
) {

    override var x
        get() = 0
        set(value) = Unit

    override var y
        get() = 0
        set(value) = Unit
}
