package net.benwoodworth.fastcraft.impl.bukkit.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigSectionTests
import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import org.bukkit.configuration.file.YamlConfiguration

/**
 * Tests for [BukkitConfigSection].
 */
class BukkitConfigSectionTests : ConfigSectionTests() {

    override fun createInstance(): ConfigSection {
        return BukkitConfigSection(YamlConfiguration())
    }
}
