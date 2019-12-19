package net.benwoodworth.fastcraft.crafting.view

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class RecipeViewModel(
    val recipe: FcItem,
    private val textFactory: FcTextFactory
) {
    fun updateButton(button: FcGuiButton) {
        button.apply {
            copyItem(recipe)

            description = mutableListOf(
                textFactory.createFcText("Ingredients:"),
                textFactory.createFcText(
                    text = "  ${recipe.amount}x ",
                    extra = listOf(
                        recipe.name
                    )
                )
            )

            if (recipe.lore.any()) {
                description = description + textFactory.createFcText() + recipe.lore
            }
        }
    }
}
