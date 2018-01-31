package net.benwoodworth.fastcraft.api.gui.layout

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.GuiElement
import net.benwoodworth.fastcraft.api.gui.button.GuiButton
import net.benwoodworth.fastcraft.api.gui.event.EventGuiLayoutChange
import java.util.*

/**
 * The button layout of a GUI.
 */
abstract class GuiLayoutAbstract(
        width: Int,
        height: Int
) : GuiLayout {

    override val changeListener = Listener<EventGuiLayoutChange>()

    override var width = width
        protected set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    override var height = height
        protected set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    private val elements = LinkedList<GuiElement>()

    override fun addElement(element: GuiElement) {
        if (elements.any { it === element }) {
            elements.remove(element)
        } else {
            element.changeListener += changeListener::notifyHandlers
        }

        elements.addFirst(element)
        changeListener.notifyHandlers(EventGuiLayoutChange())
    }

    override fun removeElement(element: GuiElement) {
        elements.removeIf { it === element }
        element.changeListener -= changeListener::notifyHandlers
    }

    override fun getElement(x: Int, y: Int): GuiElement? {
        return elements.firstOrNull { it.contains(x, y) }
    }

    override fun getButton(x: Int, y: Int): GuiButton? {
        val element = getElement(x, y)
        return when (element) {
            is GuiButton -> return element
            is GuiLayout -> element.getButton(
                    x - element.x,
                    y - element.y
            )
            else -> null
        }
    }
}
