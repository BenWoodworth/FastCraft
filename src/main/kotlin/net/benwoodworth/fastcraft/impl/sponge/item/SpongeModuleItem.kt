package net.benwoodworth.fastcraft.impl.sponge.item

import dagger.Module
import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.impl.sponge.item.recipe.SpongeRecipeProvider

/**
 * Sponge implementation of [ModuleItem].
 */
@Module
class SpongeModuleItem(
        private val plugin: Any
) : ModuleItem {

    override fun itemBuilder(): Item.Builder {
        TODO("not implemented")
    }

    override fun recipeProvider(): RecipeProvider {
        return SpongeRecipeProvider(plugin)
    }
}
