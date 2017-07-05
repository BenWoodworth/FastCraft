package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.ImplementationTests
import org.junit.Test
import org.junit.Assert.*
import java.nio.file.Path

/**
 * Tests for [Config].
 */
abstract class ConfigTests : ImplementationTests<Config>() {

    /** The path to the config file. */
    abstract val configFile: Path

    @Test
    fun `after setting the header, getting the header should be the same`() {
        val headerBuilder = StringBuilder()
        for (ch in 0..255) {
            headerBuilder.append(ch.toChar())
        }

        val expectedHeader = headerBuilder.toString()

        val testInstance = newTestInstance()
        testInstance.header = expectedHeader
        assertEquals(expectedHeader, testInstance.header)
    }

    @Test
    fun `after setting header to null, the header should be null`() {
        val testInstance = newTestInstance()
        testInstance.header = null
        assertNull(testInstance.header)
    }
}
