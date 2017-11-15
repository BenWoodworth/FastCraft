package net.benwoodworth.fastcraft.dependencies.bukkit.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigSection
import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigSectionTests
import org.bukkit.configuration.file.YamlConfiguration

/**
 * Tests for [BukkitConfigSection].
 */
class BukkitConfigSectionTests : ConfigSectionTests() {

    override fun createInstance(): ConfigSection {
        return BukkitConfigSection(YamlConfiguration())
    }
}
