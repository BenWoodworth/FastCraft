package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.ImplementationTests
import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for [ConfigSection].
 */
abstract class ConfigSectionTests : ImplementationTests<ConfigSection>() {

    @Test
    fun `getString() and setString() set and return the correct Strings`() {
        for (testValue in listOf(
                "This test string has one line",
                "This is a test string!\nIt has two lines :D",
                "This one has special characters \r\n\t"
        )) {
            val testInstance = newTestInstance()
            testInstance.setString("key", testValue)
            assertEquals(testValue, testInstance.getString("key"))
        }
    }

    @Test
    fun `getInt() and setInt() set and return the correct Ints`() {
        for (testValue in -1000..1000) {
            val testInstance = newTestInstance()
            testInstance.setInt("key", testValue)
            assertEquals(testValue, testInstance.getInt("key"))
        }
    }

    @Test
    fun `getBoolean() and setBoolean() set and return the correct Booleans`() {
        for (testValue in listOf(true, false)) {
            val testInstance = newTestInstance()
            testInstance.setBoolean("key", testValue)
            assertEquals(testValue, testInstance.getBoolean("key"))
        }
    }
}
