package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModelFactory
import net.benwoodworth.fastcraft.crafting.presenter.FastCraftGuiPresenter
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiViewFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import javax.inject.Inject

class FastCraftGuiFactory @Inject constructor(
    private val fastCraftGuiModelFactory: FastCraftGuiModelFactory,
    private val fastCraftGuiViewFactory: FastCraftGuiViewFactory,
) {
    fun createFastCraftGui(player: FcPlayer): FastCraftGui {
        val model = fastCraftGuiModelFactory.create(player)
        val view = fastCraftGuiViewFactory.create(player)
        val presenter = FastCraftGuiPresenter(model, view)

        return FastCraftGui(presenter)
    }
}
