package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.ImplTest
import org.junit.Test
import org.junit.Assert.*
import java.nio.file.Files
import java.nio.file.Path

/**
 * Tests for [Config].
 */
abstract class ConfigTests : ImplTest<Config>() {

    /** The path to the config file. */
    abstract val configFile: Path

    private fun testConfigValues(config: Config) {
        assertEquals("should load correct header",
                "This is the config header!",
                config.header
        )

        assertEquals("should load key with periods",
                "Value",
                config.get<String>("I'm a String")
        )

        val nested = config.getSection("nested").getSection("values")
        assertEquals("Should load correct nested values",
                "bar",
                nested.get<String>("foo")
        )

        assertEquals("should load correct String",
                "I'm a String",
                config.get<String>("string")
        )

        assertEquals("should load correct Int",
                1234,
                config.get<String>("int")
        )

        assertEquals("should load correct String list",
                listOf("first", "second", "third"),
                config.get<List<String>>("string_list")
        )

        assertEquals("should load correct Int list",
                listOf(0, 1, 2),
                config.get<List<Int>>("int_list")
        )
    }

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

    @Test
    fun `should load config with correct information`() {
        testInstance.load(configFile)

        testConfigValues(testInstance)
    }

    @Test
    fun `should save and load config correctly`() {
        testInstance.load(configFile)

        val tempFile = Files.createTempFile(null, null)
        testInstance.save(tempFile)

        val loaded = createInstance()
        loaded.load(tempFile)
        Files.delete(tempFile)

        testConfigValues(testInstance)
    }
}
