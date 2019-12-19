package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.model.PageCollection
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class PageButtonView(
    val button: FcGuiButton,
    private val recipePages: PageCollection<FastCraftRecipe>,
    @Provided val itemTypes: FcItemTypes,
    @Provided val textFactory: FcTextFactory,
    @Provided val textColors: FcTextColors
) {
    init {
        button.apply {
            itemType = itemTypes.ironSword

            description = listOf(
                textFactory.createFcText("Left click: next page", color = textColors.aqua),
                textFactory.createFcText("Right click: previous page", color = textColors.aqua),
                textFactory.createFcText("Shift click: first page", color = textColors.aqua)
            )

            hideItemDetails()
        }
    }

    fun update() {
        button.apply {
            text = textFactory.createFcText(
                text = "Page ${recipePages.pageNumber}/${recipePages.pageCount}",
                color = textColors.green
            )
        }
    }
}
