package net.benwoodworth.fastcraft.sponge.dependencies.item

import dagger.Module
import net.benwoodworth.fastcraft.core.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.core.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.core.dependencies.item.recipe.RecipeProvider

/**
 * Sponge implementation of [ModuleItem].
 */
@Module
class SpongeModuleItem : ModuleItem {

    override fun itemBuilder(): ItemBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun recipeProvider(): RecipeProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
