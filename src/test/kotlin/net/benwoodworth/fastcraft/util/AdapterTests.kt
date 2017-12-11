package net.benwoodworth.fastcraft.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Tests for [Adapter].
 */
class AdapterTests {

    @Test
    fun `when comparing equality, should be unequal when comparing to non-adapters`() {
        for (adapted in -100..100) {
            val adapter = object : Adapter<Int>(adapted) {}

            assertNotEquals(adapted, adapter)
        }
    }

    @Test
    fun `when comparing equality, should be equal to adapters with equal bases`() {
        for (adapted in -100..100) {
            val adapterA = object : Adapter<Int>(adapted) {}
            val adapterB = object : Adapter<Int>(adapted) {}

            assertEquals(adapterA, adapterB)
        }
    }

    @Test
    fun `when comparing equality, should not be equal to adapters with unequal bases`() {
        for (adapted in -100..100) {
            val adapterA = object : Adapter<Int>(adapted) {}
            val adapterB = object : Adapter<Int>(adapted + 1) {}

            assertNotEquals(adapterA, adapterB)
        }
    }

    @Test
    fun `when calculating hash code, should use hash code of base`() {
        for (adapted in -100..100) {
            val adapter = object : Adapter<Int>(adapted) {}

            val expected = adapted.hashCode()
            val actual = adapter.hashCode()

            assertEquals(expected, actual)
        }
    }
}
