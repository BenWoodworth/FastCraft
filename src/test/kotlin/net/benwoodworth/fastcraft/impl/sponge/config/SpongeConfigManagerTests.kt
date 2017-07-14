package net.benwoodworth.fastcraft.impl.sponge.config

import net.benwoodworth.fastcraft.dependencies.config.ConfigManagerTests
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [SpongeConfigManager].
 */
class SpongeConfigManagerTests : ConfigManagerTests() {

    override val testConfigPath: Path = Paths.get(
            javaClass.classLoader.getResource("config-sponge.conf").toURI()
    )

    override fun createInstance() = SpongeConfigManager()
}
