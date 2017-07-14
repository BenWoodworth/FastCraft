package net.benwoodworth.fastcraft.dependencies.config

import net.benwoodworth.fastcraft.ImplementationTests
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import java.nio.file.Path

/**
 * Tests for [Config].
 */
abstract class ConfigTests : ImplementationTests<Config>() {

    /** The path to the config file. */
    abstract val configFile: Path

    @Test
    fun `after setting the header, getting the header should be the same`() {
        for (expected in listOf(
                emptyList(),
                listOf(""),
                listOf("", ""),
                listOf("This test string has one line"),
                listOf(" This header begins with a space"),
                listOf("This is a test string!", "It has two lines :D")
        )) {
            testInstance.header = expected
            val actual = testInstance.header
            assertEquals(expected, actual)
        }
    }
}
