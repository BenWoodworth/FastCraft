package net.benwoodworth.fastcraft.crafting.presenter

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView
import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class FastCraftGuiPresenter(
    private val player: FcPlayer,
    private val model: FastCraftGuiModel,
    private val view: FastCraftGuiView,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors,
    @Provided private val itemTypes: FcItemTypes
) {
    init {
        model.refreshRecipes()
//        view.recipes = model.recipes


    }

    fun openGui() {
        view.gui.open()
    }

    private fun onGuiClose(event: FcGuiCloseEvent) {
        player.sendMessage(
            textFactory.createFcText("Thank you for using FastCraft!")
        )
    }
}
