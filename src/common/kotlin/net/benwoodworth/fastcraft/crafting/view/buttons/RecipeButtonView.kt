package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import kotlin.properties.Delegates.observable

@AutoFactory
class RecipeButtonView(
    val button: FcGuiButton,
    @Provided val itemTypes: FcItemTypes,
    @Provided val textFactory: FcTextFactory,
    @Provided val textColors: FcTextColors
) {
    var recipe by observable<FcCraftingRecipePrepared?>(null) { _, _, newRecipe ->
        update()
    }

    init {
        button.clear()
    }

    fun update() {
        val recipe = recipe

        button.apply {
            if (recipe == null) {
                button.clear()
                return
            }

//            val preview = (recipe.craftPreview() as CancellableResult.Result).result
//            copyItem(preview.first())
//
//            description = mutableListOf(
//                textFactory.createFcText("Ingredients:")
//            )
//
//            if (preview.first().lore.any()) {
//                description = description + textFactory.createFcText() + preview.first().lore
//            }
        }
    }
}
