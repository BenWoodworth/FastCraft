//package net.benwoodworth.fastcraft.gui.crafting
//
//import com.google.auto.factory.AutoFactory
//import com.google.auto.factory.Provided
//import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
//import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
//import net.benwoodworth.fastcraft.platform.gui.mvp.MvpPresenter
//import net.benwoodworth.fastcraft.platform.player.FcPlayer
//import net.benwoodworth.fastcraft.platform.text.FcText
//import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
//import javax.inject.Provider
//
//@AutoFactory
//class CraftingGuiPresenter(
//    private val player: FcPlayer,
//
//    @Provided view: net.benwoodworth.fastcraft.gui.crafting.CraftingGuiView,
//    @Provided model: net.benwoodworth.fastcraft.gui.crafting.CraftingGuiModel,
//    @Provided taskBuilder: Provider<FcTaskFactory>,
//    @Provided private val textFactory: FcText.Factory
//) : MvpPresenter<net.benwoodworth.fastcraft.gui.crafting.CraftingGuiView, net.benwoodworth.fastcraft.gui.crafting.CraftingGuiModel>(
//    view,
//    model
//) {
//
//    private val taskCycleResults = taskBuilder.get() //TODO Make disablable
//        .delay(20) //TODO make configurable
//        .interval(20)
//        .execute { view.elementRecipeList.showNextResult() }
//
//    init {
//        view.elementRecipeList.recipeClickListener += ::onRecipeClick
//
//        view.buttonWorkbench.clickListener += ::onWorkbenchClick
//        view.buttonMultiplier.clickListener += ::onMultiplierClick
//        view.buttonRefresh.clickListener += ::onRefreshClick
//        view.buttonPage.clickListener += ::onPageClick
//    }
//
//    fun open() = view.gui.open(player)
//
//    private fun onRecipeClick(event: net.benwoodworth.fastcraft.gui.crafting.elements.ElementRecipeList.RecipeClickEvent) {
//
//    }
//
//    private fun onWorkbenchClick(event: FcGuiClickEvent) {
//        event.player?.sendMessage(
//            textFactory
//                .buildText("Workbench clicked!")
//                .build()
//        )
//    }
//
//    private fun onMultiplierClick(event: FcGuiClickEvent) {
//        event.player?.sendMessage(
//            textFactory
//                .buildText("Multiplier clicked!")
//                .build()
//        )
//    }
//
//    private fun onRefreshClick(event: FcGuiClickEvent) {
//        event.player?.sendMessage(
//            textFactory
//                .buildText("Refresh clicked!")
//                .build()
//        )
//    }
//
//    private fun onPageClick(event: FcGuiClickEvent) {
//        event.player?.sendMessage(
//            textFactory
//                .buildText("Page clicked!")
//                .build()
//        )
//    }
//
//    private fun onClose(event: FcGuiCloseEvent) {
//        taskCycleResults.cancel()
//    }
//
//    override fun updateView() {
//
//    }
//}
