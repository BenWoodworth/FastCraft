package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

object `BukkitFcConfigBuilder$1_13_R0_1` : FcConfigBuilder {

    override fun empty() = `BukkitFcConfigBuilderLoaded$1_13_R0_1` {
        YamlConfiguration()
    }

    override fun file(file: Path) = `BukkitFcConfigBuilderLoaded$1_13_R0_1` {
        YamlConfiguration.loadConfiguration(file.toFile())
    }
}
