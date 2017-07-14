package net.benwoodworth.fastcraft.impl.sponge.item

import dagger.Module
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider

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
