package net.benwoodworth.fastcraft.implementations.sponge.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigSectionTests
import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
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
