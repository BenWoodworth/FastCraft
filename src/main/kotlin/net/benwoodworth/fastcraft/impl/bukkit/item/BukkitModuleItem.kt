package net.benwoodworth.fastcraft.impl.bukkit.item

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider

/**
 * Bukkit implementation of [ModuleItem].
 */
@Module
class BukkitModuleItem : ModuleItem {

    @Provides
    override fun itemBuilder(): Item.Builder {
        TODO("not implemented")
    }

    @Provides
    override fun recipeProvider(): RecipeProvider {
        TODO("not implemented")
    }
}
