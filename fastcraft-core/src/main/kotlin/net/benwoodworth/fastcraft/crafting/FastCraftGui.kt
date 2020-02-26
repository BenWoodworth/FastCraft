package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.crafting.presenter.FastCraftGuiPresenter

class FastCraftGui(
    private val presenter: FastCraftGuiPresenter
) {
    fun open() {
        presenter.openGui()
    }
}
