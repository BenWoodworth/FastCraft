package net.benwoodworth.fastcraft.dependencies.sponge.item

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.dependencies.sponge.item.recipe.SpongeRecipeProvider

/**
 * Sponge implementation of [ModuleItem].
 */
@Module
class SpongeModuleItem(
        private val plugin: Any
) : ModuleItem {

    @Provides
    override fun itemBuilder(): Item.Builder {
        return SpongeItem.Builder()
    }

    @Provides
    override fun recipeProvider(): RecipeProvider {
        return SpongeRecipeProvider(plugin)
    }
}
