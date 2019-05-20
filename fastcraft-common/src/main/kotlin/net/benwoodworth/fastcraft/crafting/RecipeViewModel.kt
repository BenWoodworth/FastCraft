package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class RecipeViewModel(
    val recipe: FcItem,
    itemFactory: FcItemFactory,
    textFactory: FcTextFactory,
    textColors: FcTextColors
) {

    val buttonItem: FcItem = run {
        with(itemFactory) {
            with(textFactory) {
                with(textColors) {
                    val lore = mutableListOf(
                        createFcText("Ingredients:"),
                        createFcText(
                            text = "  ${recipe.amount}x ",
                            extra = listOf(
                                recipe.displayName ?: recipe.type.name
                            )
                        )
                    )

                    if (recipe.lore.any()) {
                        lore += createFcText()
                        lore += recipe.lore
                    }

                    return@run recipe.copy(
                        lore = lore
                    )
                }
            }
        }
    }
}