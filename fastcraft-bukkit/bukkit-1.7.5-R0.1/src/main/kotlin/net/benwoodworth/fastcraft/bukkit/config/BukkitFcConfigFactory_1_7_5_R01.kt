package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import javax.inject.Inject

class BukkitFcConfigFactory_1_7_5_R01 @Inject constructor(
) : BukkitFcConfigFactory {
    override fun createEmpty(): FcConfig {
        return BukkitFcConfig_1_7_5_R01(YamlConfiguration())
    }

    override fun createFromPath(value: Path): FcConfig {
        return BukkitFcConfig_1_7_5_R01(
            YamlConfiguration.loadConfiguration(value.toFile())
        )
    }
}
