package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.ImplementationTests
import net.benwoodworth.fastcraft.core.dependencies.item.ItemBuilder

/**
 * Tests for [Gui].
 */
abstract class GuiTests : ImplementationTests<Gui>() {

    abstract fun createGuiBuilder(): GuiBuilder

    abstract fun createItemBuilder(): ItemBuilder

}
