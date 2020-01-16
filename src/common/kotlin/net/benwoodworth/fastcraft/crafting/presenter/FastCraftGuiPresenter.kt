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

    private val firstRecipeIndex = (recipesPage - 1) * recipesPerPage

    init {
        view.pageButton.listener = PageButtonListener()

        view.recipeButtons.forEachIndexed { i, button ->
            button.listener = RecipeButtonListener(i)
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

        view.recipeButtons.forEachIndexed { i, button ->
            button.fastCraftRecipe = model.recipes.getOrNull(i + firstRecipeIndex)
            button.update()
        }
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
                else -> maxOf(1, page - 1)
            }
            updatePage()
        }

        override fun onPageFirst() {
            recipesPage = 1
            updatePage()
        }
    }

    private inner class RecipeButtonListener(
        private val recipeButtonIndex: Int
    ) : RecipeButtonView.Listener {
        override fun onCraft(button: RecipeButtonView, recipe: FastCraftRecipe, dropResults: Boolean) {
            val recipeIndex = recipeButtonIndex + firstRecipeIndex
            val craftSucceeded = model.craftRecipe(recipeIndex, dropResults)

            if (craftSucceeded) {
                button.fastCraftRecipe = model.recipes[recipeIndex]
                button.update()
            }
        }
    }
}
