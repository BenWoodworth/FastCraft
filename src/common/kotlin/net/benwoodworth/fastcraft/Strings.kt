package net.benwoodworth.fastcraft

import net.benwoodworth.localeconfig.api.LocaleApi
import java.util.*

object Strings {
    // region locale keys
    private const val LOCALIZATION_TRANSLATOR = "localization.translator"
    private const val LOCALIZATION_TRANSLATOR_FROM = "localization.translator.from"
    private const val GUI_TITLE = "gui.title"
    private const val GUI_RECIPE_INGREDIENTS = "gui.recipe.ingredients"
    private const val GUI_RECIPE_INGREDIENTS_ITEM = "gui.recipe.ingredients.item"
    private const val GUI_RECIPE_RESULTS = "gui.recipe.results"
    private const val GUI_RECIPE_RESULTS_ITEM = "gui.recipe.results.item"
    private const val GUI_RECIPE_ID = "gui.recipe.id"
    private const val GUI_TOOLBAR_PAGE_TITLE = "gui.toolbar.page.title"
    private const val GUI_TOOLBAR_PAGE_DESCRIPTION_0 = "gui.toolbar.page.description.0"
    private const val GUI_TOOLBAR_PAGE_DESCRIPTION_1 = "gui.toolbar.page.description.1"
    private const val GUI_TOOLBAR_PAGE_DESCRIPTION_2 = "gui.toolbar.page.description.2"
    private const val GUI_TOOLBAR_REFRESH_TITLE = "gui.toolbar.refresh.title"
    private const val GUI_TOOLBAR_REFRESH_DESCRIPTION_0 = "gui.toolbar.refresh.description.0"
    private const val GUI_TOOLBAR_AMOUNT_TITLE = "gui.toolbar.amount.title"
    private const val GUI_TOOLBAR_AMOUNT_DESCRIPTION_0 = "gui.toolbar.amount.description.0"
    private const val GUI_TOOLBAR_AMOUNT_DESCRIPTION_1 = "gui.toolbar.amount.description.1"
    private const val GUI_TOOLBAR_AMOUNT_DESCRIPTION_2 = "gui.toolbar.amount.description.2"
    private const val GUI_TOOLBAR_WORKBENCH_TITLE = "gui.toolbar.workbench.title"
    private const val GUI_TOOLBAR_WORKBENCH_DESCRIPTION_0 = "gui.toolbar.workbench.description.0"
    private const val GUI_TOOLBAR_WORKBENCH_DESCRIPTION_1 = "gui.toolbar.workbench.description.1"
    // endregion

    fun load() {
        LocaleApi.loadLocales("fastcraft")
    }

    fun localizationTranslator(locale: Locale): String {
        return LocaleApi.getText(locale, LOCALIZATION_TRANSLATOR)
            ?.toString()
            ?: LOCALIZATION_TRANSLATOR
    }

    fun localizationTranslatorFrom(locale: Locale): String {
        return LocaleApi.getText(locale, LOCALIZATION_TRANSLATOR_FROM)
            ?.toString()
            ?: LOCALIZATION_TRANSLATOR_FROM
    }

    fun guiTitle(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TITLE)
            ?.substitute()
            ?: GUI_TITLE
    }

    fun guiRecipeIngredients(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_RECIPE_INGREDIENTS)
            ?.substitute()
            ?: GUI_RECIPE_INGREDIENTS
    }

    fun guiRecipeIngredientsItem(locale: Locale, amount: Int, item: String): String {
        return LocaleApi.getText(locale, GUI_RECIPE_INGREDIENTS_ITEM)
            ?.substitute(
                "amount", amount,
                "item", item
            )
            ?: "$GUI_RECIPE_INGREDIENTS_ITEM(amount=$amount, item=$item)"
    }

    fun guiRecipeResults(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_RECIPE_RESULTS)
            ?.substitute()
            ?: GUI_RECIPE_RESULTS
    }

    fun guiRecipeResultsItem(locale: Locale, amount: Int, item: String): String {
        return LocaleApi.getText(locale, GUI_RECIPE_RESULTS_ITEM)
            ?.substitute(
                "amount", amount,
                "item", item
            )
            ?: "$GUI_RECIPE_RESULTS_ITEM(amount=$amount, item=$item)"
    }

    fun guiRecipeId(locale: Locale, id: String): String {
        return LocaleApi.getText(locale, GUI_RECIPE_ID)
            ?.substitute(
                "id", id
            )
            ?: "$GUI_RECIPE_ID(id=$id)"
    }

    fun guiToolbarPageTitle(locale: Locale, page: Int, total: Int): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_PAGE_TITLE)
            ?.substitute(
                "page", page,
                "total", total
            )
            ?: "$GUI_TOOLBAR_PAGE_TITLE(page=$page, total=$total)"
    }

    fun guiToolbarPageDescription0(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_PAGE_DESCRIPTION_0)
            ?.substitute()
            ?: GUI_TOOLBAR_PAGE_DESCRIPTION_0
    }

    fun guiToolbarPageDescription1(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_PAGE_DESCRIPTION_1)
            ?.substitute()
            ?: GUI_TOOLBAR_PAGE_DESCRIPTION_1
    }

    fun guiToolbarPageDescription2(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_PAGE_DESCRIPTION_2)
            ?.substitute()
            ?: GUI_TOOLBAR_PAGE_DESCRIPTION_2
    }

    fun guiToolbarRefreshTitle(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_REFRESH_TITLE)
            ?.substitute()
            ?: GUI_TOOLBAR_REFRESH_TITLE
    }

    fun guiToolbarRefreshDescription0(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_REFRESH_DESCRIPTION_0)
            ?.substitute()
            ?: GUI_TOOLBAR_REFRESH_DESCRIPTION_0
    }

    fun guiToolbarAmountTitle(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_AMOUNT_TITLE)
            ?.substitute()
            ?: GUI_TOOLBAR_AMOUNT_TITLE
    }

    fun guiToolbarAmountDescription0(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_AMOUNT_DESCRIPTION_0)
            ?.substitute()
            ?: GUI_TOOLBAR_AMOUNT_DESCRIPTION_0
    }

    fun guiToolbarAmountDescription1(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_AMOUNT_DESCRIPTION_1)
            ?.substitute()
            ?: GUI_TOOLBAR_AMOUNT_DESCRIPTION_1
    }

    fun guiToolbarAmountDescription2(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_AMOUNT_DESCRIPTION_2)
            ?.substitute()
            ?: GUI_TOOLBAR_AMOUNT_DESCRIPTION_2
    }

    fun guiToolbarWorkbenchTitle(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_WORKBENCH_TITLE)
            ?.substitute()
            ?: GUI_TOOLBAR_WORKBENCH_TITLE
    }

    fun guiToolbarWorkbenchDescription0(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_WORKBENCH_DESCRIPTION_0)
            ?.substitute()
            ?: GUI_TOOLBAR_WORKBENCH_DESCRIPTION_0
    }

    fun guiToolbarWorkbenchDescription1(locale: Locale): String {
        return LocaleApi.getText(locale, GUI_TOOLBAR_WORKBENCH_DESCRIPTION_1)
            ?.substitute()
            ?: GUI_TOOLBAR_WORKBENCH_DESCRIPTION_1
    }
}
