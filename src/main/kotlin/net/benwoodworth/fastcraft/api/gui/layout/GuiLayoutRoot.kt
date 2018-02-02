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

    override var x = 0
        set(value) = Unit

    override var y = 0
        set(value) = Unit

    override var width = width
        set(value) = Unit

    override var height = height
        set(value) = Unit
}
