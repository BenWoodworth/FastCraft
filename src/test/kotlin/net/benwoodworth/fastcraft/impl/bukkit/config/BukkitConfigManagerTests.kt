package net.benwoodworth.fastcraft.impl.bukkit.config

import net.benwoodworth.fastcraft.dependencies.config.ConfigManagerTests
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [BukkitConfigManager].
 */
class BukkitConfigManagerTests : ConfigManagerTests() {

    override val testConfigPath: Path = Paths.get(
            javaClass.classLoader.getResource("config-sponge.conf").toURI()
    )

    override fun createInstance() = BukkitConfigManager()
}
