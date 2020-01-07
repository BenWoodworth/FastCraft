package net.benwoodworth.fastcraft.crafting.presenter

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView

class FastCraftGuiPresenter(
    private val model: FastCraftGuiModel,
    private val view: FastCraftGuiView
) {
    fun openGui() {
        view.gui.open()
    }
}
