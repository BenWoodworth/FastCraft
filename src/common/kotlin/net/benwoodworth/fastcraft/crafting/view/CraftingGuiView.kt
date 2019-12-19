package net.benwoodworth.fastcraft.crafting.view

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.crafting.model.PageCollection
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class CraftingGuiView(
    player: FcPlayer,
    @Provided guiFactory: FcGuiFactory,
    @Provided workbenchButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.WorkbenchButtonViewFactory,
    @Provided pageButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.PageButtonViewFactory,
    @Provided multiplierButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.MultiplierButtonViewFactory,
    @Provided refreshButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.RefreshButtonViewFactory,
    @Provided private val textFactory: FcTextFactory
) {
    private val gui = guiFactory.openChestGui(
        player = player,
        title = textFactory.createFcText("FastCraft"),
        height = 6
    )

    private val width = gui.layout.width
    private val height = gui.layout.height

    private val recipePages = PageCollection<FastCraftRecipe>((width - 2) * height)

    private val workbenchButton = workbenchButtonFactory.create(gui.layout.getButton(width - 1, 0))
    private val multiplierButton = multiplierButtonFactory.create(gui.layout.getButton(width - 1, 1))
    private val refreshButton = refreshButtonFactory.create(gui.layout.getButton(width - 1, 2))
    private val pageButton = pageButtonFactory.create(gui.layout.getButton(width - 1, height - 1), recipePages)

    init {
        pageButton.update()

        recipePages.onChange {
            pageButton.update()
        }

        pageButton.button.onClick += ::onPageButtonClick
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
}
