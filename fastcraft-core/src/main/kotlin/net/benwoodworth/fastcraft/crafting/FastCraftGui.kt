package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.presenter.FastCraftGuiPresenter
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import javax.inject.Inject

class FastCraftGui(
    private val presenter: FastCraftGuiPresenter,
) {
    fun open() {
        presenter.openGui()
    }

    class Factory @Inject constructor(
        private val fastCraftGuiModelFactory: FastCraftGuiModel.Factory,
        private val fastCraftGuiViewFactory: FastCraftGuiView.Factory,
    ) {
        fun createFastCraftGui(player: FcPlayer): FastCraftGui {
            val model = fastCraftGuiModelFactory.create(player)
            val view = fastCraftGuiViewFactory.create(player)
            val presenter = FastCraftGuiPresenter(model, view)

            return FastCraftGui(presenter)
        }
    }

}
