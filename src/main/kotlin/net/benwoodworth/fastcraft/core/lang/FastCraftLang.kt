package net.benwoodworth.fastcraft.core.lang

interface FastCraftLang {

    fun guiTitle(): String
    fun guiIngredientsLabel(): String
    fun guiIngredientsItem(item: String, amount: String): String
    fun guiResultsLabel(): String
    fun guiResultsItem(item: String, amount: String): String
    fun guiToolbarPageTitle(page: Int, total: Int): String
    fun guiToolbarPageLore(page: Int, total: Int): List<String>
    fun guiToolbarWorkbenchTitle(page: Int, total: Int): String
    fun guiToolbarWorkbenchLore(page: Int, total: Int): List<String>
    fun guiToolbarMultiplierTitle(page: Int, total: Int): String
    fun guiToolbarMultiplierLore(page: Int, total: Int): List<String>
    fun guiToolbarRefreshTitle(page: Int, total: Int): String
    fun guiToolbarRefreshLore(page: Int, total: Int): List<String>
}