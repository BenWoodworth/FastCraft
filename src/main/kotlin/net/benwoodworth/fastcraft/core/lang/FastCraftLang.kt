package net.benwoodworth.fastcraft.core.lang

import net.benwoodworth.fastcraft.dependencies.text.Text

interface FastCraftLang {

    fun guiTitle(): Text
    fun guiIngredientsLabel(): Text
    fun guiIngredientsItem(item: String, amount: Int): Text
    fun guiResultsLabel(): Text
    fun guiResultsItem(item: String, amount: Int): Text
    fun guiToolbarPageTitle(page: Int, total: Int): Text
    fun guiToolbarPageDescription(page: Int, total: Int): List<Text>
    fun guiToolbarWorkbenchTitle(): Text
    fun guiToolbarWorkbenchDescription(): List<Text>
    fun guiToolbarMultiplierTitle(multiplier: Int): Text
    fun guiToolbarMultiplierDescription(multiplier: Int): List<Text>
    fun guiToolbarRefreshTitle(): Text
    fun guiToolbarRefreshDescription(): List<Text>
}