package net.benwoodworth.fastcraft.crafting.view

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.PageCollection
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import kotlin.properties.Delegates

@AutoFactory
class CraftingGuiView(
    player: FcPlayer,
    @Provided guiFactory: FcGuiFactory,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors,
    @Provided private val itemTypes: FcItemTypes
) {
    private val gui = guiFactory.openChestGui(
        player = player,
        title = textFactory.createFcText("FastCraft"),
        height = 6
    )

    private val width = gui.layout.width
    private val height = gui.layout.height

    private val workbenchButton = gui.layout.getButton(width - 1, 0).apply {
        itemType = itemTypes.craftingTable

        text = textFactory.createFcText("Crafting Grid", color = textColors.green)

        description = listOf(
            textFactory.createFcText("Open a 3x3 crafting grid", color = textColors.aqua),
            textFactory.createFcText(
                color = textColors.green,
                extra = listOf(
                    textFactory.createFcText("Use "),
                    textFactory.createFcText("/fc toggle", color = textColors.aqua, italic = true),
                    textFactory.createFcText(" to disable FastCraft")
                )
            )
        )

        hideItemDetails()
    }

    private val multiplierButton = gui.layout.getButton(width - 1, 1).apply {
        itemType = itemTypes.anvil

        text = textFactory.createFcText("Crafting Multiplier", color = textColors.green)

        description = listOf(
            textFactory.createFcText("Left/right click to increase/decrease", color = textColors.aqua),
            textFactory.createFcText("Shift click to increment by 1", color = textColors.aqua),
            textFactory.createFcText("Middle click to reset to 1x", color = textColors.aqua)
        )

        hideItemDetails()
    }

    private val refreshButton = gui.layout.getButton(width - 1, 2).apply {
        itemType = itemTypes.netherStar

        text = textFactory.createFcText("Refresh", color = textColors.green)

        description = listOf(
            textFactory.createFcText("Refresh the FastCraft interface", color = textColors.aqua)
        )

        hideItemDetails()
    }

    private val pageButton = gui.layout.getButton(width - 1, height - 1).apply {
        itemType = itemTypes.ironSword

        description = listOf(
            textFactory.createFcText("Left click: next page", color = textColors.aqua),
            textFactory.createFcText("Right click: previous page", color = textColors.aqua),
            textFactory.createFcText("Shift click: first page", color = textColors.aqua)
        )

        hideItemDetails()
    }

    private val recipeButtons = List((width - 2) * height) { i ->
        gui.layout.getButton(i % (width - 2), i / (width - 2))
    }

    private val recipePages: PageCollection<RecipeViewModel> =
        PageCollection((width - 2) * height)

    var multiplier: Int by Delegates.observable(1) { _, _, new ->
        multiplierButton.amount = new
    }

    init {
        updatePageButton()
        updateRecipeButtons()

        recipePages.onChange {
            updatePageButton()
            updateRecipeButtons()
        }

        pageButton.onClick += ::onPageButtonClick
    }

    private fun onPageButtonClick(event: FcGuiClickEvent) {
        with(recipePages) {
            when {
                event.isShiftClick -> when {
                    event.isPrimaryClick -> setPage(1)
                    event.isSecondaryClick -> setPage(pageCount)
                }
                event.isPrimaryClick -> setPage(pageNumber - 1)
                event.isSecondaryClick -> setPage(pageNumber + 1)
                else -> return
            }
        }
    }

    private fun updatePageButton() {
        pageButton.apply {
            text = textFactory.createFcText(
                text = "Page ${recipePages.pageNumber}/${recipePages.pageCount}",
                color = textColors.green
            )
        }
    }

    private fun updateRecipeButtons() {
//        recipePages.page.forEachIndexed { i: Int, recipe: FcItem? ->
//            recipeButtons[i].apply {
//                when (recipe) {
//                    null -> clear()
//                    else -> copyItem(recipe)
//                }
//            }
//        }
    }
}
