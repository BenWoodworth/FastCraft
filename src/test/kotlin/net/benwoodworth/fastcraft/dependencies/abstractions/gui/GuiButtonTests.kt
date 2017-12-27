package net.benwoodworth.fastcraft.dependencies.abstractions.gui

import net.benwoodworth.fastcraft.dependencies.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.gui.GuiButtonBasic
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests for [GuiButton].
 */
class GuiButtonTests {

    @Test
    fun `when changing the button's item, the change listener should be notified`() {
        val button = GuiButtonBasic()

        var notified = false
        button.changeListener += { -> notified = true }

        button.item = null
        assertTrue(notified)
    }
}
