package net.benwoodworth.fastcraft.dependencies.sponge.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.Config
import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigTests
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [SpongeConfig].
 */
class SpongeConfigTests : ConfigTests() {

    override val configFile: Path
        get() = Paths.get(javaClass.classLoader.getResource("config-sponge.conf").toURI())

    override fun createInstance(): Config {
        val config = HoconConfigurationLoader.builder().build().createEmptyNode()
        return SpongeConfig(config)
    }
}
