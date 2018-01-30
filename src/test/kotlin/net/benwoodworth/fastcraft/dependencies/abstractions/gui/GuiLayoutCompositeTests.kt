package net.benwoodworth.fastcraft.dependencies.abstractions.gui

import net.benwoodworth.fastcraft.api.gui.GuiLayoutComposite

/**
 * Tests for [GuiLayoutComposite].
 */
class GuiLayoutCompositeTests : GuiLayoutTests() {

    override fun createInstance() = GuiLayoutComposite(2, 2)
}
