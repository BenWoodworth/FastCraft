package net.benwoodworth.fastcraft.core.dependencies.gui

/**
 * Tests for [GuiLayoutComposite].
 */
class GuiLayoutCompositeTests : GuiLayoutTests() {

    override fun createInstance() = GuiLayoutComposite(2, 2)
}
