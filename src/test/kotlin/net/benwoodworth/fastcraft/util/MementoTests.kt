package net.benwoodworth.fastcraft.util

import org.junit.Assert.*
import org.junit.jupiter.api.Test

/**
 * Tests for [Memento]
 */
class MementoTests {

    private class MockCopyable(val generation: Int = 0) : Copyable<MockCopyable> {
        var copyCount = 0
            private set

        override fun copy() = MockCopyable(generation + 1)
    }

    @Test
    fun `when constructing, should get copy of instance`() {
        val copyable = MockCopyable()

        Memento(copyable)

        assertEquals(0, copyable.copyCount)
    }

    @Test
    fun `when getting the instance, should return a new copy`() {
        val memento = Memento(MockCopyable())
        val get = memento.get()

        assertEquals(2, get.generation)
    }
}
