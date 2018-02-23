package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import kotlin.reflect.KProperty

/**
 * A property delegate, for use with [GuiElement] properties, that
 * triggers a [GuiEventLayoutChange] when its value is changed.
 */
class GuiLayoutChanger<T> @JvmOverloads constructor(
        private var value: T,
        private val get: (field: T) -> T = { it }
) {

    operator fun getValue(thisRef: GuiElement, property: KProperty<*>) = get(value)

    operator fun setValue(thisRef: GuiElement, property: KProperty<*>, newValue: T) {
        if (value !== newValue) {
            value = newValue
            thisRef.changeListener.notifyHandlers(GuiEventLayoutChange(thisRef.region))
        }
    }
}