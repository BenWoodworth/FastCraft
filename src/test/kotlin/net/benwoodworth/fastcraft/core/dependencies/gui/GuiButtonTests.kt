package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.item.Item
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

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

    @Test
    fun `when getting or setting the button's item, should use a copy`() {
        val item = mock(Item::class.java)
        val itemCopy = mock(Item::class.java)

        `when`(item.copy()).thenReturn(itemCopy)

        val button = GuiButton()

        button.item = item
        verify(item).copy()

        button.item
        verify(itemCopy).copy()
    }
}
