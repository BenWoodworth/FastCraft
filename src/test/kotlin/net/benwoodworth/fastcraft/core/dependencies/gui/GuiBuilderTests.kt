package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.ImplementationTests
import net.benwoodworth.fastcraft.core.dependencies.text.TextBuilder
import org.junit.Assert.*
import org.junit.jupiter.api.Test

/**
 * Tests for [Gui].
 */
abstract class GuiBuilderTests : ImplementationTests<GuiBuilder>() {

    abstract fun createTextBuilder(): TextBuilder

    @Test
    fun `when setting the height, the built gui should have that height`() {
        for (expected in 1..6) {
            val gui = createInstance()
                    .setHeight(expected)
                    .build()

            val actual = gui.height
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when setting the title, the built gui should have that title`() {
        val expected = createTextBuilder()
                .text("This is the expected text")
                .bold(true)
                .italic(true)
                .underlined(true)
                .obfuscated(false)
                .build()

        val gui = createInstance()
                .setTitle(expected)
                .build()

        val actual = gui.title
        assertEquals(expected, actual)
    }

    @Test
    fun `when setting the layout, the built gui should have that layout`() {
        val expected = GuiLayoutComposite(10, 20)

        val gui = createInstance()
                .setLayout(expected)
                .build()

        val actual = gui.layout
        assertEquals(expected, actual)
    }
}
