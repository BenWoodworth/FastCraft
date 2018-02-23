package net.benwoodworth.fastcraft.core.gui.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.core.gui.crafting.elements.ElementRecipeList
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.dependencies.api.mvp.MvpPresenter
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.dependencies.server.FcTaskBuilder
import javax.inject.Provider

@AutoFactory
class CraftingGuiPresenter(
        private val player: FcPlayer,

        @Provided view: CraftingGuiView,
        @Provided model: CraftingGuiModel,
        @Provided taskBuilder: Provider<FcTaskBuilder>,
        @Provided private val textBuilder: Provider<FcTextBuilder>
) : MvpPresenter<CraftingGuiView, CraftingGuiModel>(view, model) {

    private val taskCycleResults = taskBuilder.get() //TODO Make disablable
            .delay(20) //TODO make configurable
            .interval(20)
            .execute { view.elementRecipeList.showNextResult() }

    init {
        view.elementRecipeList.recipeClickListener += ::onRecipeClick

        view.buttonWorkbench.clickListener += ::onWorkbenchClick
        view.buttonMultiplier.clickListener += ::onMultiplierClick
        view.buttonRefresh.clickListener += ::onRefreshClick
        view.buttonPage.clickListener += ::onPageClick
    }

    fun open() = view.gui.open(player)

    private fun onRecipeClick(event: ElementRecipeList.RecipeClickEvent) {

    }

    private fun onWorkbenchClick(event: GuiEventClick) {
        event.player?.sendMessage(
                textBuilder.get()
                        .text("Workbench clicked!")
                        .build()
        )
    }

    private fun onMultiplierClick(event: GuiEventClick) {
        event.player?.sendMessage(
                textBuilder.get()
                        .text("Multiplier clicked!")
                        .build()
        )
    }

    private fun onRefreshClick(event: GuiEventClick) {
        event.player?.sendMessage(
                textBuilder.get()
                        .text("Refresh clicked!")
                        .build()
        )
    }

    private fun onPageClick(event: GuiEventClick) {
        event.player?.sendMessage(
                textBuilder.get()
                        .text("Page clicked!")
                        .build()
        )
    }

    private fun onClose(event: GuiEventClose) {
        taskCycleResults.cancel()
    }

    override fun updateView() {

    }
}