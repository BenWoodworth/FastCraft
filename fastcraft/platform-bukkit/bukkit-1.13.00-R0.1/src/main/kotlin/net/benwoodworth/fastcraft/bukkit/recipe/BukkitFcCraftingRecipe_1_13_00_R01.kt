package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

sealed class BukkitFcCraftingRecipe_1_13_00_R01 : BukkitFcCraftingRecipe {

    class Shaped(
        override val base: ShapedRecipe
    ) : BukkitFcCraftingRecipe_1_13_00_R01() {

        override val id: String
            get() = base.key.toString()

        override val ingredients: List<FcIngredient>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    }

    class Shapeless(
        override val base: ShapelessRecipe
    ) : BukkitFcCraftingRecipe_1_13_00_R01() {

        override val id: String
            get() = base.key.toString()

        override val ingredients: List<FcIngredient>
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    }
}

