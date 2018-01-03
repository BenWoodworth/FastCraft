package net.benwoodworth.fastcraft.implementations.sponge.item

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.implementations.sponge.item.recipe.SpongeRecipeProvider

/**
 * Sponge implementation of [ModuleItem].
 */
@Module
class SpongeModuleItem(
        private val plugin: Any
) : ModuleItem {

    @Provides
    override fun itemBuilder(): ItemBuilder {
        return SpongeItemBuilder()
    }

    @Provides
    override fun recipeProvider(): RecipeProvider {
        return SpongeRecipeProvider(plugin)
    }
}
