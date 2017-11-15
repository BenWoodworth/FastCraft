package net.benwoodworth.fastcraft.dependencies.bukkit.config

import net.benwoodworth.fastcraft.dependencies.abstractions.config.Config
import net.benwoodworth.fastcraft.dependencies.abstractions.config.ConfigTests
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Tests for [BukkitConfig].
 */
class BukkitConfigTests : ConfigTests() {

    override val configFile: Path
        get() = Paths.get(javaClass.classLoader.getResource("config-bukkit.yml").toURI())

    override fun createInstance(): Config {
        return BukkitConfig(YamlConfiguration())
    }
}
