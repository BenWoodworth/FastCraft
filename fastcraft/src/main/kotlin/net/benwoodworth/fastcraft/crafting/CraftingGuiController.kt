package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class CraftingGuiController(
    private val player: FcPlayer,
    private val model: CraftingGuiModel,
    private val view: CraftingGuiView,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val itemFactory: FcItemFactory,
    private val itemTypes: FcItemTypes
) {

    init {
        view.gui.onClose += ::onGuiClose

        view.workbenchButton.onClick += ::onWorkbenchButtonClick
        view.multiplierButton.onClick += ::onMultiplierButtonClick
        view.refreshButton.onClick += ::onRefreshButtonClick
        view.pageButton.onClick += ::onPageButtonClick

        view.recipeButtons.forEachIndexed { i, button ->
            button.onClick { event ->
                onRecipeButtonClick(event, i)
            }
        }

        updateView()
    }

    private fun updateView() {
        with(textFactory) {
            view.workbenchButton.item = itemFactory.createFcItem(
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

            view.multiplierButton.item = itemFactory.createFcItem(
                type = itemTypes.anvil,
                displayName = createFcText("Crafting Multiplier", color = textColors.green),
                lore = listOf(
                    createFcText("Left/right click to increase/decrease", color = textColors.aqua),
                    createFcText("Shift click to increment by 1", color = textColors.aqua),
                    createFcText("Middle click to reset to 1x", color = textColors.aqua)
                )
            )

            view.refreshButton.item = itemFactory.createFcItem(
                type = itemTypes.netherStar,
                displayName = createFcText("Refresh", color = textColors.green),
                lore = listOf(
                    createFcText("Refresh the FastCraft interface", color = textColors.aqua)
                )
            )

            view.pageButton.item = itemFactory.createFcItem(
                type = itemTypes.ironSword,
                displayName = createFcText(
                    "Page ${model.pages.current}/${model.pages.total}",
                    color = textColors.green
                ),
                lore = listOf(
                    createFcText("Left click: next page", color = textColors.aqua),
                    createFcText("Right click: previous page", color = textColors.aqua),
                    createFcText("Middle click: first page", color = textColors.aqua)
                )
            )
        }
    }

    private fun onGuiClose(event: FcGuiCloseEvent) {
        with(textFactory) {
            player.sendMessage(
                createFcText("Thank you for using FastCraft!")
            )
        }
    }

    private fun onWorkbenchButtonClick(event: FcGuiClickEvent) {
        view.gui.close()

        // TODO
    }

    private fun onMultiplierButtonClick(event: FcGuiClickEvent) {
        when {
            event.isShiftClick -> when {
                event.isPrimaryClick -> model.multiplier.increment()
                event.isSecondaryClick -> model.multiplier.decrement()
                event.isMiddleClick -> model.multiplier.maximize()
                else -> return
            }
            else -> when {
                event.isPrimaryClick -> model.multiplier.increase()
                event.isSecondaryClick -> model.multiplier.decrease()
                event.isMiddleClick -> model.multiplier.minimize()
                else -> return
            }
        }

        updateView()
    }

    private fun onRefreshButtonClick(event: FcGuiClickEvent) {
        with(textFactory) {
            player.sendMessage(
                createFcText("Refresh button clicked")
            )
        }
    }

    private fun onPageButtonClick(event: FcGuiClickEvent) {
        when {
            event.isPrimaryClick -> model.pages.next()
            event.isSecondaryClick -> model.pages.previous()
            event.isMiddleClick -> model.pages.first()
            else -> return
        }

        updateView()
    }

    private fun onRecipeButtonClick(event: FcGuiClickEvent, index: Int) {
        with(textFactory) {
            player.sendMessage(
                createFcText("Recipe Button #$index clicked")
            )
        }
    }
}