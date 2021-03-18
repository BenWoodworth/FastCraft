package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class FcRecipeProvider_Bukkit_1_13 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    private val server: Server,
    fcCraftingRecipeFactory: FcCraftingRecipe_Bukkit.Factory,
) : FcRecipeProvider_Bukkit_1_7(
    plugin = plugin,
    bukkitVersion = bukkitVersion,
    server = server,
    fcCraftingRecipeFactory = fcCraftingRecipeFactory
) {
    init {
        warmUpRecipeIterator()
    }

    private fun warmUpRecipeIterator() {
        server.recipeIterator().forEach { _ -> }
    }
}
