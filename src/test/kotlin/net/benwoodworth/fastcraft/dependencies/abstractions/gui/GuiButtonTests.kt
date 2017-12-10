package net.benwoodworth.fastcraft.dependencies.abstractions.gui

import org.junit.Assert.*
import org.junit.jupiter.api.Test

/**
 * Tests for [GuiButton].
 */
class GuiButtonTests {

    @Test
    fun `when changing the button's item, the change listener should be notified`() {
        val button = GuiButton()

        var notified = false
        button.changeListener += { -> notified = true }

        button.item = null
        assertTrue(notified)
    }
}
