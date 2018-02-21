package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import kotlin.reflect.KProperty

/**
 * A property delegate, for use with [GuiElement] properties, that
 * triggers a [GuiEventLayoutChange] when its value is changed.
 */
class GuiLayoutChanger<T>(private var value: T) {

    operator fun getValue(thisRef: GuiElement, property: KProperty<*>) = value

    operator fun setValue(thisRef: GuiElement, property: KProperty<*>, newValue: T) {
        if (value !== newValue) {
            value = newValue
            thisRef.changeListener.notifyHandlers(GuiEventLayoutChange(thisRef.region))
        }
    }
}