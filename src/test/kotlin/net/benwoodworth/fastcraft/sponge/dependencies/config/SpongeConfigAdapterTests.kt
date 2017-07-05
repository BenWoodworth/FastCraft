package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.Config
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigTests
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import org.junit.jupiter.api.BeforeEach
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [SpongeConfigAdapter].
 */
class SpongeConfigAdapterTests : ConfigTests() {

    override val configFile: Path
        get() = Paths.get(javaClass.classLoader.getResource("config-sponge.conf").toURI())

    override fun newTestInstance(): Config {
        val config = HoconConfigurationLoader.builder().build().createEmptyNode()
        return SpongeConfigAdapter(config)
    }
}
