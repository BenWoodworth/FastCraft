package net.benwoodworth.fastcraft.crafting.presenter

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView
import net.benwoodworth.fastcraft.crafting.view.buttons.*
import kotlin.math.ceil

class FastCraftGuiPresenter(
    private val model: FastCraftGuiModel,
    private val view: FastCraftGuiView,
) {
    private var recipesPage = 1

    private val recipesPerPage: Int
        get() = view.recipeButtons.count()

    private val recipesPageCount: Int
        get() = maxOf(1, ceil(model.recipes.count().toDouble() / recipesPerPage).toInt())

    private val firstRecipeIndex: Int
        get() = (recipesPage - 1) * recipesPerPage

    init {
        model.listener = ModelListener()

        view.workbenchButton?.listener = WorkbenchButtonListener()
        view.craftAmountButton?.listener = CraftAmountButtonListener()
        view.pageButton?.listener = PageButtonListener()
        view.refreshButton?.listener = RefreshButtonListener()

        view.recipeButtons.forEachIndexed { i, button ->
            button.listener = RecipeButtonListener(i)
        }

        model.refreshRecipes()
        updatePage()
    }

    fun openGui() {
        view.gui.open()
    }

    private fun updateRecipes() {
        view.recipeButtons.forEachIndexed { i, button ->
            button.fastCraftRecipe = model.recipes.getOrNull(i + firstRecipeIndex)
            button.update()
        }
    }

    private fun updatePage() {
        view.pageButton?.apply {
            page = recipesPage
            pageCount = recipesPageCount
            update()
        }

        updateRecipes()
    }

    private fun updateCraftAmount() {
        model.updateCraftAmounts()

        view.craftAmountButton?.apply {
            craftAmount = model.craftAmount ?: 1
            update()
        }

        updateRecipes()
    }

    private inner class ModelListener : FastCraftGuiModel.Listener {
        override fun onRecipesChange(recipes: List<FastCraftRecipe?>) {
            updatePage()
        }
    }

    private inner class WorkbenchButtonListener : WorkbenchButtonView.Listener {
        override fun onOpenWorkbench() {
            model.openCraftingTable()
        }
    }

    private inner class CraftAmountButtonListener : CraftAmountButtonView.Listener {
        override fun onIncrement() {
            val amount = model.craftAmount
            model.craftAmount = when {
                amount == null -> 8
                amount == 64 -> null
                amount % 8 == 0 -> amount + 8
                else -> (amount / 8 + 1) * 8
            }
            updateCraftAmount()
        }

        override fun onIncrementByOne() {
            val amount = model.craftAmount
            model.craftAmount = when (amount) {
                null -> 2
                64 -> null
                else -> amount + 1
            }
            updateCraftAmount()
        }

        override fun onDecrement() {
            val amount = model.craftAmount
            model.craftAmount = when {
                amount == null -> 64
                amount <= 8 -> null
                amount % 8 == 0 -> amount - 8
                else -> (amount / 8) * 8
            }
            updateCraftAmount()
        }

        override fun onDecrementByOne() {
            val amount = model.craftAmount
            model.craftAmount = when (amount) {
                null -> 64
                2 -> null
                else -> amount - 1
            }
            updateCraftAmount()
        }

        override fun onReset() {
            model.craftAmount = null
            updateCraftAmount()
        }
    }

    private inner class RefreshButtonListener : RefreshButtonView.Listener {
        override fun onRefresh() {
            model.refreshRecipes()

            recipesPage = 1
            updatePage()
        }
    }

    private inner class PageButtonListener : PageButtonView.Listener {
        override fun onPageNext() {
            recipesPage = when (val page = recipesPage) {
                recipesPageCount -> 1
                else -> minOf(page + 1, recipesPageCount)
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
        private val recipeButtonIndex: Int,
    ) : RecipeButtonView.Listener {
        override fun onCraft(button: RecipeButtonView, recipe: FastCraftRecipe, dropResults: Boolean) {
            val recipeIndex = recipeButtonIndex + firstRecipeIndex
            val craftSucceeded = model.craftRecipe(recipeIndex, dropResults)

            if (craftSucceeded) {
                button.fastCraftRecipe = model.recipes[recipeIndex]
            }

            model.updateInventoryItemAmounts()
            updateRecipes()
        }
    }
}
