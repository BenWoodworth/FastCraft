package net.benwoodworth.fastcraft.core.lang

import net.benwoodworth.fastcraft.dependencies.text.Text

interface FastCraftLang {

    fun guiTitle(): Text
    fun guiIngredientsLabel(): Text
    fun guiIngredientsItem(item: String, amount: Int): Text
    fun guiResultsLabel(): Text
    fun guiResultsItem(item: String, amount: Int): Text
    fun guiToolbarPageTitle(page: Int, total: Int): Text
    fun guiToolbarPageLore(page: Int, total: Int): List<Text>
    fun guiToolbarWorkbenchTitle(): Text
    fun guiToolbarWorkbenchLore(): List<Text>
    fun guiToolbarMultiplierTitle(multiplier: Int): Text
    fun guiToolbarMultiplierLore(multiplier: Int): List<Text>
    fun guiToolbarRefreshTitle(): Text
    fun guiToolbarRefreshLore(): List<Text>
}