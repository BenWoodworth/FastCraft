package net.benwoodworth.fastcraft.sponge.dependencies

import net.benwoodworth.fastcraft.core.dependencies.config.Config
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigTests
import net.benwoodworth.fastcraft.sponge.dependencies.config.SpongeConfigAdapter
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [SpongeConfigAdapter].
 */
class SpongeConfigAdapterTests : ConfigTests() {

    override val configFile: Path
        get() = Paths.get(javaClass.classLoader.getResource("config-sponge.conf").toURI())

    override fun createInstance(): Config {
        val config = HoconConfigurationLoader.builder().build().load()
        return SpongeConfigAdapter(config)
    }
}
