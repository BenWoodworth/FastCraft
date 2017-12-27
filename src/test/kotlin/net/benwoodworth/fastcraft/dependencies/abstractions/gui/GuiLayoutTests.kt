package net.benwoodworth.fastcraft.dependencies.abstractions.gui

import net.benwoodworth.fastcraft.ImplementationTests
import net.benwoodworth.fastcraft.dependencies.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.gui.GuiButtonBasic
import net.benwoodworth.fastcraft.dependencies.gui.GuiLayout
import org.junit.Assert.*
import org.junit.jupiter.api.Test

/**
 * Tests for [GuiLayout].
 */
abstract class GuiLayoutTests : ImplementationTests<GuiLayout>() {

//    @Test
//    fun `when a layout's width changes, should notify change listener`() {
//        var layoutNotifyCount = 0
//        testInstance.changeListener += { -> layoutNotifyCount++ }
//
//        testInstance.width++
//
//        assertEquals(1, layoutNotifyCount)
//    }
//
//    @Test
//    fun `when a layout's height changes, should notify change listener`() {
//        var layoutNotifyCount = 0
//        testInstance.changeListener += { -> layoutNotifyCount++ }
//
//        testInstance.height++
//
//        assertEquals(1, layoutNotifyCount)
//    }

    @Test
    fun `when replacing a button, should notify change listener once`() {
        testInstance.setButton(0, 0, GuiButtonBasic())

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.setButton(0, 0, GuiButtonBasic())

        assertEquals(1, layoutNotifyCount)
    }

    @Test
    fun `when adding a button, should notify change listener once`() {
        testInstance.removeButton(0, 0)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.setButton(0, 0, GuiButtonBasic())

        assertEquals(1, layoutNotifyCount)
    }

    @Test
    fun `when removing an existing button, should notify change listener once`() {
        testInstance.setButton(0, 0, GuiButtonBasic())

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.removeButton(0, 0)

        assertEquals(1, layoutNotifyCount)
    }

    @Test
    fun `when removing a non-existent button, should not notify change listener`() {
        testInstance.removeButton(0, 0)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        testInstance.removeButton(0, 0)

        assertEquals(0, layoutNotifyCount)
    }

    @Test
    fun `when adding a button, should listen to its change listener`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, button)

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        button.item = null

        assertTrue(layoutNotified)
    }

    @Test
    fun `when adding a button, should remove button in the same position`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, GuiButtonBasic())
        testInstance.setButton(0, 0, button)

        assertTrue(button === testInstance.getButton(0, 0))
    }

    @Test
    fun `when removing a button, should stop listening to its change listener`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, button)
        testInstance.removeButton(0, 0)

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        button.item = null

        assertFalse(layoutNotified)
    }

    @Test
    fun `when replacing a button, should stop listening to its change listener`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, button)
        testInstance.setButton(0, 0, GuiButtonBasic())

        var layoutNotified = false
        testInstance.changeListener += { -> layoutNotified = true }

        button.item = null

        assertFalse(layoutNotified)
    }

    @Test
    fun `when a button is on a layout twice, change listener should be notified twice`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, button)
        testInstance.setButton(1, 0, button)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        button.item = null

        assertEquals(2, layoutNotifyCount)
    }

    @Test
    fun `when adding a button twice, and removing one, should still listen only to the other`() {
        val button = GuiButtonBasic()

        testInstance.setButton(0, 0, button)
        testInstance.setButton(1, 0, button)

        testInstance.removeButton(1, 0)

        var layoutNotifyCount = 0
        testInstance.changeListener += { -> layoutNotifyCount++ }

        button.item = null

        assertEquals(1, layoutNotifyCount)
    }

    @Test
    fun `when adding a button, should be able to add inside and outside of bounds`() {
        for (x in -3..testInstance.width + 3) {
            for (y in -3..testInstance.height + 3) {
                val button = GuiButtonBasic()
                testInstance.setButton(x, y, button)

                assertTrue(button === testInstance.getButton(x, y))
            }
        }
    }
}
