package net.benwoodworth.fastcraft.core.dependencies.config

import net.benwoodworth.fastcraft.ImplementationTests
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

/**
 * Tests for [ConfigManager].
 */
abstract class ConfigManagerTests : ImplementationTests<ConfigManager>() {

    /** The path to the test config. */
    abstract val testConfigPath: Path

    private fun verifyConfigValues(config: Config) {
        assertEquals("should load correct header",
                listOf("This is the config header!", "It has more than one line!"),
                config.header
        )

        assertEquals("should load key with periods",
                "Value",
                config.getString("key.with.periods")
        )

        assertEquals("Should load correct nested values",
                "bar",
                config["nested"]["values"].getString("foo")
        )

        assertEquals("should load correct String",
                "I'm a String",
                config.getString("string")
        )

        assertEquals("should load correct String list",
                listOf("first", "second", "third"),
                config.getStringList("string_list")
        )

        assertEquals("should load correct Int",
                1234,
                config.getInt("int")
        )

        assertEquals("should load correct Int list",
                listOf(0, 1, 2),
                config.getIntList("int_list")
        )
    }

    @Test
    fun `when loading a config file, correct values should be loaded`() {
        val config = testInstance.loadConfig(testConfigPath)
        verifyConfigValues(config)
    }

    @Test
    fun `when saving a config file, correct values should be saved`() {
        val config = testInstance.loadConfig(testConfigPath)

        val tempConfig = Files.createTempFile(null, null)
        testInstance.saveConfig(tempConfig, config)
        val testConfig = testInstance.loadConfig(tempConfig)
        Files.deleteIfExists(tempConfig)

        verifyConfigValues(testConfig)
    }
}
