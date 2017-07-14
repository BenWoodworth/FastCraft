package net.benwoodworth.fastcraft.impl.sponge.config

import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.dependencies.config.ConfigSectionTests
import ninja.leaping.configurate.hocon.HoconConfigurationLoader

/**
 * Tests for [SpongeConfigSection].
 */
class SpongeConfigSectionTests : ConfigSectionTests() {

    override fun createInstance(): ConfigSection {
        val config = HoconConfigurationLoader.builder().build().createEmptyNode()
        return SpongeConfigSection(config)
    }
}
