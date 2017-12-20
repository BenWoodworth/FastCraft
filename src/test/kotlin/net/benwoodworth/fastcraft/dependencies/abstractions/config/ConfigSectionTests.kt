package net.benwoodworth.fastcraft.dependencies.abstractions.config

import net.benwoodworth.fastcraft.ImplementationTests
import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import org.junit.Assert.*
import org.junit.jupiter.api.Test

/**
 * Tests for [ConfigSection].
 */
abstract class ConfigSectionTests : ImplementationTests<ConfigSection>() {

    @Test
    fun `when getting a non-existent key, null should be returned`() {
        val key = "key"
        assertFalse(testInstance.hasKey(key))

        val string = testInstance.getString(key)
        assertNull(string)

        val stringList = testInstance.getStringList(key)
        assertNull(stringList)

        val int = testInstance.getInt(key)
        assertNull(int)

        val intList = testInstance.getIntList(key)
        assertNull(intList)

        val boolean = testInstance.getBoolean(key)
        assertNull(boolean)
    }

    @Test
    fun `when setting a value to null, value should be null`() {
        testInstance.setString("string", "test")
        testInstance.setString("string", null)
        assertNull(testInstance.getString("string"))

        testInstance.setStringList("stringList", listOf("hi"))
        testInstance.setStringList("stringList", null)
        assertNull(testInstance.getStringList("string"))

        testInstance.setInt("int", 5)
        testInstance.setInt("int", null)
        assertNull(testInstance.getInt("int"))

        testInstance.setIntList("intList", listOf(1, 2, 3))
        testInstance.setIntList("intList", null)
        assertNull(testInstance.getIntList("intList"))

        testInstance.setBoolean("boolean", true)
        testInstance.setBoolean("boolean", null)
        assertNull(testInstance.getBoolean("boolean"))
    }

    @Test
    fun `when getting and setting strings, should store and retrieve correctly`() {
        for (expected in listOf(
                "",
                "This test string has one line",
                "This is a test string!\nIt has two lines :D",
                "This one has special characters \r\n\t"
        )) {
            val testInstance = createInstance()
            testInstance.setString("key", expected)

            val actual = testInstance.getString("key")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when getting and setting string lists, should store and retrieve correctly`() {
        for (expected in listOf(
                emptyList(),
                listOf("first", "second", "third"),
                listOf("this is", "a list", "of", "string values")
        )) {
            val testInstance = createInstance()
            testInstance.setStringList("key", expected)

            val actual = testInstance.getStringList("key")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when getting and setting ints, should store and retrieve correctly`() {
        for (expected in -1000..1000) {
            val testInstance = createInstance()
            testInstance.setInt("key", expected)

            val actual = testInstance.getInt("key")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when getting and setting int lists, should store and retrieve correctly`() {
        for (expected in listOf(
                emptyList(),
                (-10..10).toList(),
                (-1000..1000).step(17).toList()
        )) {
            val testInstance = createInstance()
            testInstance.setIntList("key", expected)

            val actual = testInstance.getIntList("key")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `when getting and setting booleans, should store and retrieve correctly`() {
        for (expected in listOf(true, false)) {
            val testInstance = createInstance()
            testInstance.setBoolean("key", expected)

            val actual = testInstance.getBoolean("key")
            assertEquals(expected, actual)
        }
    }
}
