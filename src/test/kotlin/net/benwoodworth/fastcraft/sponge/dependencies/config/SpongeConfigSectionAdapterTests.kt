package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSectionTests
import ninja.leaping.configurate.hocon.HoconConfigurationLoader

/**
 * Tests for [SpongeConfigSectionAdapter].
 */
class SpongeConfigSectionAdapterTests : ConfigSectionTests() {

    override fun createInstance(): ConfigSection {
        val config = HoconConfigurationLoader.builder().build().createEmptyNode()
        return SpongeConfigSectionAdapter(config)
    }
}
