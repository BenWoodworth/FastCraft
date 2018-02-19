package net.benwoodworth.fastcraft.dependencies.api.gui.layout

import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.item.Item
import java.util.*

/**
 * Abstract implementation of [GuiLayout].
 */
abstract class GuiLayoutAbstract(
    region: GuiRegion.Rectangle
) : GuiLayout, GuiElementAbstract(region) {

    private val elements = LinkedList<GuiElement>()

    override fun addElement(element: GuiElement) {
        if (elements.any { it === element }) {
            elements.remove(element)
        } else {
            element.changeListener += ::onLayoutChange
        }

        elements.addFirst(element)
        changeListener.notifyHandlers(GuiEventLayoutChange(element))
    }

    override fun removeElement(element: GuiElement) {
        elements.removeIf { it === element }
        element.changeListener -= changeListener::notifyHandlers
    }

    override fun getElement(x: Int, y: Int): GuiElement? {
        return elements.firstOrNull {
            (x in it.x..(it.x + it.width)) && (y in it.y..(it.y + it.height))
        }
    }

    override fun onClick(x: Int, y: Int, event: GuiEventClick) {
        getElement(x, y)?.let {
            it.onClick(x - it.x, y - it.y, event)
        }
    }

    override fun getItem(x: Int, y: Int): Item? {
        return getElement(x, y)?.let {
            it.getItem(x - it.x, y - it.y)
        }
    }

    private fun onLayoutChange(event: GuiEventLayoutChange) {
        changeListener.notifyHandlers(event.copy(
                region = GuiRegionOffset(event.region, x, y)
        ))
    }
}
