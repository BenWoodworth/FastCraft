package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
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
    private val itemTypes: FcItemTypes
) {
    init {
        model.refreshRecipes()
//        view.recipes = model.recipes


    }

    private fun onGuiClose(event: FcGuiCloseEvent) {
        player.sendMessage(
            textFactory.createFcText("Thank you for using FastCraft!")
        )
    }
}
