package net.benwoodworth.fastcraft.dependencies.bukkit.item

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.dependencies.bukkit.item.recipe.BukkitRecipeProvider

/**
 * Bukkit implementation of [ModuleItem].
 */
@Module
class BukkitModuleItem : ModuleItem {

    @Provides
    override fun itemBuilder(): Item.Builder {
        return BukkitItem.Builder()
    }

    @Provides
    override fun recipeProvider(): RecipeProvider {
        return BukkitRecipeProvider()
    }
}
