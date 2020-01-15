package net.benwoodworth.fastcraft.crafting.presenter

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView
import net.benwoodworth.fastcraft.crafting.view.buttons.PageButtonView
import net.benwoodworth.fastcraft.crafting.view.buttons.RecipeButtonView
import kotlin.math.ceil

class FastCraftGuiPresenter(
    private val model: FastCraftGuiModel,
    private val view: FastCraftGuiView
) {
    private var recipesPage = 1

    private val recipesPerPage: Int
        get() = view.recipeButtons.count()

    private val recipesPageCount: Int
        get() = maxOf(1, ceil(model.recipes.count().toDouble() / recipesPerPage).toInt())

    init {
        view.pageButton.listener = PageButtonListener()

        val recipeButtonListener = RecipeButtonListener()
        view.recipeButtons.forEach { button ->
            button.listener = recipeButtonListener
        }

        model.refreshRecipes()
        updatePage()
    }

    fun openGui() {
        view.gui.open()
    }

    private fun updatePage() {
        view.pageButton.apply {
            page = recipesPage
            pageCount = recipesPageCount
            update()
        }

        val firstRecipeIndex = (recipesPage - 1) * recipesPerPage
        view.recipeButtons.forEachIndexed { i, button ->
            button.fastCraftRecipe = model.recipes.getOrNull(i + firstRecipeIndex)
            button.update()
        }
    }

    private fun calculatePageCount(): Int {
        val recipeCount = model.recipes.count()
        val pageCount = ceil(recipeCount.toDouble() / recipesPerPage).toInt()
        return maxOf(1, pageCount)
    }

    private inner class PageButtonListener : PageButtonView.Listener {
        override fun onPageNext() {
            recipesPage = when (val page = recipesPage) {
                recipesPageCount -> 1
                else -> minOf(recipesPage + 1, recipesPageCount)
            }
            updatePage()
        }

        override fun onPagePrevious() {
            recipesPage = when (val page = recipesPage) {
                1 -> recipesPageCount
                else -> maxOf(1, recipesPage - 1)
            }
            updatePage()
        }

        override fun onPageFirst() {
            recipesPage = 1
            updatePage()
        }
    }

    private inner class RecipeButtonListener : RecipeButtonView.Listener {
        override fun onCraft(recipe: FastCraftRecipe, dropResults: Boolean) {
            recipe.craft(dropResults)
        }
    }
}
