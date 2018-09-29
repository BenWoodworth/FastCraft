package net.benwoodworth.fastcraft.platform.api.gui.layout

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiPosition
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.platform.api.item.FcItem
import java.util.*

/**
 * Abstract implementation of [GuiLayout].
 */
abstract class GuiLayoutAbstract<TRegion : FcGuiRegion.Positioned>(
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

    override fun getElement(position: FcGuiPosition): GuiElement? {
        return elements.firstOrNull { position in it.region }
    }

    override fun click(event: GuiEventClick) {
        getElement(event.position)?.let {
            it.click(
                event.copy(
                    position = event.position.offset(-it.region.position)
                )
            )
        }
    }

    override fun getItem(position: FcGuiPosition): FcItem? {
        return getElement(position)?.let {
            it.getItem(position.offset(it.region.position))
        }
    }

    private fun onLayoutChange(event: GuiEventLayoutChange) {
        changeListener.notifyHandlers(
            event.copy(
                region = event.region.offset(region.position)
            )
        )
    }
}
