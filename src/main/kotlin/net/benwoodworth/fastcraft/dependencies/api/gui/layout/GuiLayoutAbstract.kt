package net.benwoodworth.fastcraft.dependencies.api.gui.layout

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiPoint
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.item.Item
import java.util.*

/**
 * Abstract implementation of [GuiLayout].
 */
abstract class GuiLayoutAbstract : GuiLayout, GuiElementAbstract(region) {

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

    override fun getElement(point: GuiPoint): GuiElement? {
        return elements.firstOrNull { point in it.region }
    }

    override fun onClick(point: GuiPoint, event: GuiEventClick) {
        getElement(point)?.let {
            it.onClick(point.offset(it.region.location), event)
        }
    }

    override fun getItem(point: GuiPoint): Item? {
        return getElement(point)?.let {
            it.getItem(point.offset(it.region.location))
        }
    }

    private fun onLayoutChange(event: GuiEventLayoutChange) {
        changeListener.notifyHandlers(event.copy(
                region = GuiRegionOffset(event.region, x, y)
        ))
    }
}
