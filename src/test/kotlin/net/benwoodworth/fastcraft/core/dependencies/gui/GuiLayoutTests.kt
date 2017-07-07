package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.ImplementationTests
import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.util.Memento
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

/**
 * Tests for [GuiLayout].
 */
abstract class GuiLayoutTests : ImplementationTests<GuiLayout>() {

    @Test
    fun `when replacing a button, should notify change listener once`() {
        testInstance.setButton(0, 0, GuiButton())

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.setButton(0, 0, GuiButton())

        assertEquals(1, layoutNotifyCount)
    }

    @Test
    fun `when adding a button, should notify change listener once`() {
        testInstance.removeButton(0, 0)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.setButton(0, 0, GuiButton())

        assertEquals(layoutNotifyCount, 1)
    }

    @Test
    fun `when removing an existing button, should notify change listener once`() {
        testInstance.setButton(0, 0, GuiButton())

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.removeButton(0, 0)

        assertEquals(layoutNotifyCount, 1)
    }

    @Test
    fun `when removing a non-existent button, should not notify change listener`() {
        testInstance.removeButton(0, 0)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.removeButton(0, 0)

        assertEquals(layoutNotifyCount, 0)
    }

    @Test
    fun `when adding a button, should listen to its change listener`() {
        val button = GuiButton()

        testInstance.setButton(0, 0, button)

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        @Suppress("UNCHECKED_CAST")
        val item = mock(Memento::class.java) as Memento<Item>

        button.item = item

        assertTrue(layoutNotified)
    }

    @Test
    fun `when adding a button, should remove button in the same position`() {
        val button = GuiButton()

        testInstance.setButton(0, 0, GuiButton())
        testInstance.setButton(0, 0, button)

        assertTrue(button === testInstance.getButton(0, 0))
    }

    @Test
    fun `when removing a button, should stop listening to its change listener`() {
        val button = GuiButton()

        testInstance.setButton(0, 0, button)
        testInstance.removeButton(0, 0)

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        @Suppress("UNCHECKED_CAST")
        val item = mock(Memento::class.java) as Memento<Item>

        button.item = item

        assertFalse(layoutNotified)
    }

    @Test
    fun `when replacing a button, should stop listening to its change listener`() {
        val button = GuiButton()

        testInstance.setButton(0, 0, button)
        testInstance.setButton(0, 0, GuiButton())

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        @Suppress("UNCHECKED_CAST")
        val item = mock(Memento::class.java) as Memento<Item>

        button.item = item

        assertFalse(layoutNotified)
    }
}
