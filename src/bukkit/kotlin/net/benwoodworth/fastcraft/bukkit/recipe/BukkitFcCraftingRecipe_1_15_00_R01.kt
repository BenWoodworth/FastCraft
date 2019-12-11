package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

sealed class BukkitFcCraftingRecipe_1_15_00_R01 : BukkitFcCraftingRecipe {
    class Shaped(
        private val base: ShapedRecipe
    ) : BukkitFcCraftingRecipe_1_15_00_R01() {
        override val id: String
            get() = base.key.toString()

        override val ingredients: List<FcIngredient>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun prepare(ingredients: List<FcItem>): FcCraftingRecipePrepared {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun equals(other: Any?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun hashCode(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class Shapeless(
        private val base: ShapelessRecipe
    ) : BukkitFcCraftingRecipe_1_15_00_R01() {
        override val id: String
            get() = base.key.toString()

        override val ingredients: List<FcIngredient>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun prepare(ingredients: List<FcItem>): FcCraftingRecipePrepared {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun equals(other: Any?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun hashCode(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

