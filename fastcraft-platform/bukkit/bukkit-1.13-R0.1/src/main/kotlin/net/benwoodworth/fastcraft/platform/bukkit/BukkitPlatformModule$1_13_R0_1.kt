package net.benwoodworth.fastcraft.platform.bukkit

import net.benwoodworth.fastcraft.platform.bukkit.config.`BukkitFcConfigBuilder$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.gui.`BukkitFcGuiBuilder$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.item.`BukkitFcItemBuilder$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.item.`BukkitFcItemTypes$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.player.`BukkitFcPlayerProvider$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.recipe.`BukkitFcRecipeService$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.server.`BukkitFcLogger$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.server.`BukkitFcTaskBuilder$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.text.`BukkitFcTextBuilder$1_13_R0_1`
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.nio.file.Path

@Suppress("ClassName")
inline class `BukkitPlatformModule$1_13_R0_1`(
    private val plugin: Plugin
) : BukkitPlatformModule {

    override fun getLogger(): `BukkitFcLogger$1_13_R0_1` {
        return `BukkitFcLogger$1_13_R0_1`(plugin.logger)
    }

    override fun getDataFolder(): Path {
        return plugin.dataFolder.toPath()
    }

    override fun getConfigPath(): Path {
        YamlConfiguration.loadConfiguration(File("")).get
        return getDataFolder().resolve("config.yml")
    }

    override fun createConfigBuilder(): `BukkitFcConfigBuilder$1_13_R0_1` {
        return `BukkitFcConfigBuilder$1_13_R0_1`()
    }

    override fun createGuiBuilder(): `BukkitFcGuiBuilder$1_13_R0_1` {
        return `BukkitFcGuiBuilder$1_13_R0_1`()
    }

    override fun createItemBuilder(): `BukkitFcItemBuilder$1_13_R0_1` {
        return `BukkitFcItemBuilder$1_13_R0_1`
    }

    override fun getItemTypes(): `BukkitFcItemTypes$1_13_R0_1` {
        return `BukkitFcItemTypes$1_13_R0_1`
    }

    override fun getPlayerProvider(): `BukkitFcPlayerProvider$1_13_R0_1` {
        return `BukkitFcPlayerProvider$1_13_R0_1`
    }

    override fun getRecipeService(): `BukkitFcRecipeService$1_13_R0_1` {
        return `BukkitFcRecipeService$1_13_R0_1`
    }

    override fun createTaskBuilder(): `BukkitFcTaskBuilder$1_13_R0_1` {
        return `BukkitFcTaskBuilder$1_13_R0_1`(plugin)
    }

    override fun createTextBuilder(): `BukkitFcTextBuilder$1_13_R0_1` {
        return `BukkitFcTextBuilder$1_13_R0_1`
    }
}
