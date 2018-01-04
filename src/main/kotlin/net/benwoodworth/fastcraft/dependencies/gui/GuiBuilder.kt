package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A builder for [Gui]'s.
 */
interface GuiBuilder {

    /**
     * Build the [Gui]
     *
     * @return the built [Gui]
     */
    fun build(): Gui

    /**
     * Set the [Gui] height.
     *
     * @return fluent interface
     */
    fun height(height: Int): GuiBuilder

    /**
     * Set the [Gui] title.
     *
     * @return fluent interface
     */
    fun title(title: Text): GuiBuilder
}