package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.ImplTest
import org.junit.Test
import org.junit.Assert.*
import java.nio.file.Path

/**
 * Tests for [Config].
 */
abstract class ConfigTests : ImplTest<Config>() {

    /** The path to the config file. */
    abstract val configFile: Path

    @Test
    fun `after setting the header, getting the header should be the same`() {
        val headerBuilder = StringBuilder()
        for (ch in 0..255) {
            headerBuilder.append(ch.toChar())
        }

        val expectedHeader = headerBuilder.toString()

        testInstance.header = expectedHeader

        assertEquals(expectedHeader, testInstance.header)
    }

    @Test
    fun `after setting header to null, the header should be null`() {
        testInstance.header = null
        assertNull(testInstance.header)
    }

    fun `should load config with correct information`() {
        testInstance.load(configFile)

        assertEquals("should load correct header",
                "This is the Bukkit config header!",
                testInstance.header
        )

        val nested = testInstance.getSection("nested").getSection("values")
        assertEquals("Should load correct nested values",
                "bar",
                nested.get<String>("foo")
        )

        assertEquals("should load correct String",
                "I'm a String",
                testInstance.get<String>("string")
        )

        assertEquals("should load correct Int",
                1234,
                testInstance.get<String>("int")
        )

        assertEquals("should load correct String list",
                listOf("first", "second", "third"),
                testInstance.get<List<String>>("string_list")
        )

        assertEquals("should load correct Int list",
                listOf(0, 1, 2),
                testInstance.get<List<Int>>("int_list")
        )
    }
}
