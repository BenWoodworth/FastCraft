package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipePrepared
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Memento

/**
 * Sponge implementation of [RecipePrepared].
 */
class SpongeRecipePrepared(
        override val ingredients: List<Memento<Item>>,
        override val results: List<Memento<Item>>
) : RecipePrepared {

    override fun craft(player: Player): List<Item>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
