package net.benwoodworth.fastcraft.platform.gui.layout

import net.benwoodworth.fastcraft.platform.gui.GuiLocation
import net.benwoodworth.fastcraft.platform.gui.GuiRegion
import net.benwoodworth.fastcraft.platform.gui.element.GuiElement
import net.benwoodworth.fastcraft.platform.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.platform.item.FcItem
import java.util.*

/**
 * Abstract implementation of [GuiLayout].
 */
abstract class GuiLayoutAbstract<TRegion : GuiRegion.Positioned>(
        region: TRegion
) : GuiLayout, GuiElementAbstract<TRegion>(region) {

    private val elements = LinkedList<GuiElement>()

    override fun addElement(element: GuiElement) {
        if (elements.any { it === element }) {
            elements.remove(element)
        } else {
            element.changeListener += ::onLayoutChange
        }

        elements.addFirst(element)
        changeListener.notifyHandlers(
                GuiEventLayoutChange(region.intersect(element.region))
        )
    }

    override fun removeElement(element: GuiElement) {
        elements.removeIf { it === element }
        element.changeListener -= changeListener::notifyHandlers
    }

    override fun getElement(location: GuiLocation): GuiElement? {
        return elements.firstOrNull { location in it.region }
    }

    override fun click(event: GuiEventClick) {
        getElement(event.location)?.let {
            it.click(event.copy(
                    location = event.location.offset(-it.region.location)
            ))
        }
    }

    override fun getItem(location: GuiLocation): FcItem? {
        return getElement(location)?.let {
            it.getItem(location.offset(it.region.location))
        }
    }

    private fun onLayoutChange(event: GuiEventLayoutChange) {
        changeListener.notifyHandlers(event.copy(
                region = event.region.offset(region.location)
        ))
    }
}
