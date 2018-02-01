package net.benwoodworth.fastcraft.api.gui.element

/**
 * An object that can be added to a [Gui].
 */
interface GuiElementSizable : GuiElement {

    override var width: Int

    override var height: Int
}