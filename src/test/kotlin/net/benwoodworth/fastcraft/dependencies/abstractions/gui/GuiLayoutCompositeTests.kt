package net.benwoodworth.fastcraft.dependencies.abstractions.gui

/**
 * Tests for [GuiLayoutComposite].
 */
class GuiLayoutCompositeTests : GuiLayoutTests() {

    override fun createInstance() = GuiLayoutComposite.Impl(2, 2)
}
