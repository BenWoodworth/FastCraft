package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.model.ItemAmounts
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Provider

@AutoFactory
class RecipeButtonView(
    private val button: FcGuiButton,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>
) {
    var fastCraftRecipe: FastCraftRecipe? = null

    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()
    }

    fun update() {
        button.clear()

        val fastCraftRecipe = fastCraftRecipe ?: return
        if (!fastCraftRecipe.canCraft()) return

        fastCraftRecipe.preparedRecipe.let { preparedRecipe ->
            val previewItem = preparedRecipe.resultsPreview.first()
            button.copyItem(previewItem)
            button.amount = previewItem.amount * fastCraftRecipe.multiplier

            val newDescription = mutableListOf<FcText>()

            // Results
            if (preparedRecipe.resultsPreview.count() > 1) {
                newDescription += textFactory.createFcText("Results:")

                val results = itemAmountsProvider.get()
                preparedRecipe.resultsPreview.forEach { result ->
                    results += result
                }

                results.asMap().entries
                    .sortedBy { (_, amount) -> amount }
                    .forEach { (item, amount) ->
                        newDescription += textFactory.createFcText(
                            text = "- ${amount * fastCraftRecipe.multiplier}x ",
                            extra = listOf(item.name)
                        )
                    }

                newDescription += textFactory.createFcText()
            }

            // Ingredients
            run {
                textFactory.createFcText("Ingredients:")

                val ingredients = itemAmountsProvider.get()
                preparedRecipe.ingredients.values.forEach { ingredient ->
                    ingredients += ingredient
                }

                ingredients.asMap().entries
                    .sortedBy { (_, amount) -> amount }
                    .forEach { (item, amount) ->
                        newDescription += textFactory.createFcText(
                            text = "- ${amount * fastCraftRecipe.multiplier}x ",
                            extra = listOf(item.name)
                        )
                    }
            }

            if (previewItem.lore.any()) {
                newDescription += textFactory.createFcText()
                newDescription += previewItem.lore
            }

            button.description = newDescription
        }
    }

    interface Listener {
        object Default : Listener

        fun onCraft(button: RecipeButtonView, recipe: FastCraftRecipe, dropResults: Boolean) {}
    }

    private companion object {
        val CLICK_CRAFT = FcGuiClick.Primary()
        val CLICK_CRAFT_DROP = FcGuiClick.Drop()
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val recipe = fastCraftRecipe
            if (recipe != null) {
                when (click) {
                    CLICK_CRAFT -> listener.onCraft(this@RecipeButtonView, recipe, false)
                    CLICK_CRAFT_DROP -> listener.onCraft(this@RecipeButtonView, recipe, true)
                }
            }
        }
    }
}
