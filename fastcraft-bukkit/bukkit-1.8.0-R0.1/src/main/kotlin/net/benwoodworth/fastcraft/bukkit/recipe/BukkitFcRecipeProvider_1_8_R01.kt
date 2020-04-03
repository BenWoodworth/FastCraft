package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bukkit.Server
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.InputStream
import javax.inject.Inject

open class BukkitFcRecipeProvider_1_8_R01 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    server: Server,
    recipeFactory: BukkitFcCraftingRecipe.Factory,
) : BukkitFcRecipeProvider_1_7_5_R01(
    plugin = plugin,
    bukkitVersion = bukkitVersion,
    server = server,
    recipeFactory = recipeFactory
) {
    override fun InputStream.toYamlConfiguration(): YamlConfiguration {
        return this.use { YamlConfiguration.loadConfiguration(this.reader()) }
    }
}
