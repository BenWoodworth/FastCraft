package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.util.Builder

/**
 * A builder for [Gui]'s.
 */
interface GuiBuilder : Builder<Gui> {

    /**
     * Set the [Gui] height.
     *
     * @return fluent interface
     */
    fun setHeight(height: Int): GuiBuilder

    /**
     * Set the [Gui] title.
     *
     * @return fluent interface
     */
    fun setTitle(title: Text): GuiBuilder
}
