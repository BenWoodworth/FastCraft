package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.Recipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipePrepared
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.recipe.Recipe as Sponge_Recipe

/**
 * Sponge implementation of [Recipe].
 */
class SpongeRecipe(
        baseRecipe: Sponge_Recipe
) : Recipe, Adapter<Sponge_Recipe>(baseRecipe) {

    override fun prepare(player: Player, vararg items: Item): List<RecipePrepared> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
