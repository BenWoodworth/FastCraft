package net.benwoodworth.fastcraft.core.lang

import javax.inject.Inject

class FastCraftLang @Inject constructor(
        private val json: JsonLangReader
) {

    fun guiTitle() = json.string(
            "gui.title"
    )

    fun guiRecipeIngredientsLabel() = json.string(
            "gui.ingredients.label"
    )

    fun guiRecipeIngredientsItem(item: String, amount: Int) = json.string(
            "gui.ingredients.item",
            mapOf(
                    "amount" to amount.toString(),
                    "item" to item
            )
    )

    fun guiRecipeResultsLabel() = json.string(
            "gui.recipe.results.label"
    )

    fun guiRecipeResultsItem(item: String, amount: Int) = json.string(
            "gui.recipe.results.item",
            mapOf(
                    "amount" to amount.toString(),
                    "item" to item
            )
    )

    fun guiRecipeId(id: String) = json.string(
            "gui.recipe.id",
            mapOf(
                    "id" to id
            )
    )

    fun guiToolbarPageTitle(page: Int, total: Int) = json.string(
            "gui.toolbar.page.title",
            mapOf(
                    "page" to page.toString(),
                    "total" to total.toString()
            )
    )

    fun guiToolbarPageDescription(page: Int, total: Int) = json.list(
            "gui.toolbar.page.description",
            mapOf(
                    "page" to page.toString(),
                    "total" to total.toString()
            )
    )

    fun guiToolbarWorkbenchTitle() = json.string(
            "gui.toolbar.workbench.title"
    )

    fun guiToolbarWorkbenchDescription() = json.list(
            "gui.toolbar.workbench.description"
    )

    fun guiToolbarMultiplierTitle(multiplier: Int) = json.string(
            "gui.toolbar.multiplier.title",
            mapOf(
                    "multiplier" to multiplier.toString()
            )
    )

    fun guiToolbarMultiplierDescription(multiplier: Int) = json.list(
            "gui.toolbar.multiplier.description",
            mapOf(
                    "multiplier" to multiplier.toString()
            )
    )

    fun guiToolbarRefreshTitle() = json.string(
            "gui.toolbar.refresh.title"
    )

    fun guiToolbarRefreshDescription() = json.list(
            "gui.toolbar.refresh.description"
    )
}