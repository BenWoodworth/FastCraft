package net.benwoodworth.fastcraft.core.gui.crafting.elements

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.platform.event.FcListener
import net.benwoodworth.fastcraft.platform.gui.GuiLocation
import net.benwoodworth.fastcraft.platform.gui.GuiRegion
import net.benwoodworth.fastcraft.platform.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.platform.gui.element.GuiLayoutChanger
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import javax.inject.Provider
import kotlin.math.ceil

@AutoFactory
class ElementRecipeList(
        region: GuiRegion.Rectangle,

        @Provided private val itemBuilder: Provider<FcItemBuilder>,
        @Provided private val lang: FastCraftLang,
        @Provided private val textBuilder: Provider<FcText.Builder>
) : GuiElementAbstract<GuiRegion.Rectangle>(region) {

    var page by GuiLayoutChanger(0) {
        it.coerceIn(0 until pageCount)
    }

    val pageCount get() = maxOf(1, ceil(recipes.size.toDouble() / pageSize).toInt())

    private val pageSize get() = region.width * region.height

    var recipes by GuiLayoutChanger(emptyList<FcCraftingRecipe.Prepared>())

    private var resultDisplayIndex by GuiLayoutChanger(0)

    val recipeClickListener = FcListener<RecipeClickEvent>()

    fun showNextResult() {
        resultDisplayIndex++
    }

    private fun getRecipe(location: GuiLocation): FcCraftingRecipe.Prepared? {
        return recipes.getOrNull((page * pageSize) + location.x + (location.y * region.width))
    }

    override fun click(event: GuiEventClick) {
        val recipe = getRecipe(event.location) ?: return
        recipeClickListener.notifyHandlers(RecipeClickEvent(event, recipe))
    }

    override fun getItem(location: GuiLocation): FcItem? {
        val recipe = getRecipe(location) ?: return null
        val resultIndex = resultDisplayIndex % recipe.results.size
        val resultDisplay = recipe.results[resultIndex]

        val lore = mutableListOf<FcText?>()

        lore.add(lang.guiRecipeResultsLabel())
        recipe.results.forEachIndexed { i, item ->
            var itemName = item.displayName ?: item.name

            if (i == resultIndex) {
                itemName = textBuilder.get()
                        .setText("")
                        .addExtra(itemName)
                        .build()
            }

            lore.add(lang.guiRecipeResultsItem(itemName.toString(), item.amount))
        }

        lore.add(null)
        lore.add(lang.guiRecipeIngredientsLabel())
        recipe.items.forEach { item ->
            val itemName = item.displayName ?: item.name
            lang.guiRecipeResultsItem(itemName.toString(), item.amount)
        }

        lore.add(null)
        lore.add(lang.guiRecipeId(recipe.recipe.id))

        resultDisplay.lore?.let {
            lore.add(null)
            lore.addAll(it)
        }

        return itemBuilder.get()
                .from(recipe.results[resultIndex])
                .lore(lore)
                .build()
    }

    data class RecipeClickEvent(
            val clickEvent: GuiEventClick,
            val recipe: FcCraftingRecipe.Prepared
    )
}