package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class RecipeViewModel(
    val recipe: FcItem,
    private val textFactory: FcTextFactory
) {
    fun updateButton(button: FcGuiButton) {
        with(textFactory) {
            button.apply {
                copyItem(recipe)

                description = mutableListOf(
                    createFcText("Ingredients:"),
                    createFcText(
                        text = "  ${recipe.amount}x ",
                        extra = listOf(
                            recipe.name
                        )
                    )
                )

                if (recipe.lore.any()) {
                    description = description + createFcText() + recipe.lore
                }
            }
        }
    }
}
