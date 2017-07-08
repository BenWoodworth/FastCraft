package net.benwoodworth.fastcraft.impl.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSectionTests
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
