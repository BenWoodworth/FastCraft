package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.model.ItemAmounts
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import java.util.*
import javax.inject.Provider

@AutoFactory
class RecipeButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val textConverter: FcTextConverter,
    @Provided private val sounds: FcSound.Sounds,
) {
    var fastCraftRecipe: FastCraftRecipe? = null

    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()
    }

    fun update() {
        val fastCraftRecipe = fastCraftRecipe
        if (fastCraftRecipe == null || !fastCraftRecipe.canCraft()) {
            button.clear()
            return
        }

        fastCraftRecipe.preparedRecipe.let { preparedRecipe ->
            val previewItem = preparedRecipe.resultsPreview.first()
            button.copyItem(previewItem)
            button.setAmount(previewItem.amount * fastCraftRecipe.multiplier)

            val newDescription = mutableListOf<FcText>()

            // Results
            if (preparedRecipe.resultsPreview.count() > 1) {
                newDescription += textFactory.createLegacy(
                    Strings.guiRecipeResults(locale)
                )

                val results = itemAmountsProvider.get()
                preparedRecipe.resultsPreview.forEach { result ->
                    results += result
                }

                val primaryResult = preparedRecipe.resultsPreview.first()
                val primaryResultAmount = results[primaryResult]
                results[primaryResult] = 0

                results.asMap().entries
                    .sortedByDescending { (_, amount) -> amount }
                    .let { listOf(AbstractMap.SimpleEntry(primaryResult, primaryResultAmount)) + it }
                    .forEach { (item, amount) ->
                        var itemName = textConverter.toPlaintext(item.name, locale)
                        if (item.hasMetadata) {
                            itemName += "*"
                        }

                        newDescription += textFactory.createLegacy(
                            Strings.guiRecipeResultsItem(
                                locale,
                                amount = amount * fastCraftRecipe.multiplier,
                                item = itemName
                            )
                        )
                    }

                newDescription += textFactory.createFcText()
            }

            // Ingredients
            run {
                newDescription += textFactory.createLegacy(
                    Strings.guiRecipeIngredients(locale)
                )

                val ingredients = itemAmountsProvider.get()
                preparedRecipe.ingredients.values.forEach { ingredient ->
                    ingredients += ingredient
                }

                ingredients.asMap().entries
                    .sortedByDescending { (_, amount) -> amount }
                    .forEach { (item, amount) ->
                        var itemName = textConverter.toPlaintext(item.name, locale)
                        if (item.hasMetadata) {
                            itemName += "*"
                        }

                        newDescription += textFactory.createLegacy(
                            Strings.guiRecipeIngredientsItem(
                                locale,
                                amount = amount * fastCraftRecipe.multiplier,
                                item = itemName
                            )
                        )
                    }
            }

            val recipeId = fastCraftRecipe.preparedRecipe.recipe.id
            newDescription += textFactory.createLegacy(Strings.guiRecipeId(locale, recipeId))

            if (previewItem.lore.any()) {
                newDescription += textFactory.createFcText()
                newDescription += previewItem.lore
            }

            button.setDescription(newDescription)
        }
    }

    private companion object {
        val CLICK_CRAFT = FcGuiClick.Primary()
        val CLICK_CRAFT_DROP = FcGuiClick.Drop()
    }

    interface Listener {
        object Default : Listener

        fun onCraft(button: RecipeButtonView, recipe: FastCraftRecipe, dropResults: Boolean) {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val recipe = fastCraftRecipe
            if (recipe != null) {
                val action = when (click) {
                    CLICK_CRAFT -> {
                        { listener.onCraft(this@RecipeButtonView, recipe, false) }
                    }
                    CLICK_CRAFT_DROP -> {
                        { listener.onCraft(this@RecipeButtonView, recipe, true) }
                    }
                    else -> null
                }

                action?.let {
                    gui.player.playSound(sounds.uiButtonClick)
                    action()
                }
            }
        }
    }
}
