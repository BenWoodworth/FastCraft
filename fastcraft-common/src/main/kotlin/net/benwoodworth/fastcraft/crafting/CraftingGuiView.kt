package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import kotlin.properties.Delegates

class CraftingGuiView(
    player: FcPlayer,
    guiFactory: FcGuiFactory,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val itemFactory: FcItemFactory,
    private val itemTypes: FcItemTypes
) {
    private val gui = guiFactory.openChestGui(
        player = player,
        title = textFactory.createFcText("FastCraft"),
        height = 6
    )

    private val width = gui.layout.width
    private val height = gui.layout.width

    private val workbenchButton = gui.layout.getButton(width - 1, 0)
    private val multiplierButton = gui.layout.getButton(width - 1, 1)
    private val refreshButton = gui.layout.getButton(width - 1, 2)
    private val pageButton = gui.layout.getButton(width - 1, height - 1)

    private val recipeButtons = List((width - 2) * height) { i ->
        gui.layout.getButton(i % (width - 2), i / (width - 2))
    }

    private val recipePages: PageCollection<RecipeViewModel> = PageCollection((width - 2) * height)

    var multiplier: Int by Delegates.observable(1) { _, old, new ->
        if (old != new) {
            updateMultiplierButton()
        }
    }

    init {
        updateWorkbenchButton()
        updateMultiplierButton()
        updateRefreshButton()
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

    private fun updateWorkbenchButton() {
        with(textFactory) {
            workbenchButton.item = itemFactory.createFcItem(
                type = itemTypes.craftingTable,
                displayName = createFcText("Crafting Grid", color = textColors.green),
                lore = listOf(
                    createFcText("Open a 3x3 crafting grid", color = textColors.aqua),
                    createFcText(
                        color = textColors.green,
                        extra = listOf(
                            createFcText("Use "),
                            createFcText("/fc toggle", color = textColors.aqua, italic = true),
                            createFcText(" to disable FastCraft")
                        )
                    )
                )
            )
        }
    }

    private fun updateMultiplierButton() {
        with(textFactory) {
            multiplierButton.item = itemFactory.createFcItem(
                type = itemTypes.anvil,
                displayName = createFcText("Crafting Multiplier", color = textColors.green),
                lore = listOf(
                    createFcText("Left/right click to increase/decrease", color = textColors.aqua),
                    createFcText("Shift click to increment by 1", color = textColors.aqua),
                    createFcText("Middle click to reset to 1x", color = textColors.aqua)
                )
            )
        }
    }

    private fun updateRefreshButton() {
        with(textFactory) {
            refreshButton.item = itemFactory.createFcItem(
                type = itemTypes.netherStar,
                displayName = createFcText("Refresh", color = textColors.green),
                lore = listOf(
                    createFcText("Refresh the FastCraft interface", color = textColors.aqua)
                )
            )
        }
    }

    private fun updatePageButton() {
        with(textFactory) {
            pageButton.item = itemFactory.createFcItem(
                type = itemTypes.ironSword,
                displayName = createFcText(
                    text = "Page ${recipePages.pageNumber}/${recipePages.pageCount}",
                    color = textColors.green
                ),
                lore = listOf(
                    createFcText("Right click: next page", color = textColors.aqua),
                    createFcText("Left click: previous page", color = textColors.aqua),
                    createFcText("Shift click: first/last page", color = textColors.aqua)
                )
            )
        }
    }

    private fun updateRecipeButtons() {
//        recipePages.page.forEachIndexed { i: Int, recipe: FcItem? ->
//            recipeButtons[i].item = recipe
//        }
    }
}