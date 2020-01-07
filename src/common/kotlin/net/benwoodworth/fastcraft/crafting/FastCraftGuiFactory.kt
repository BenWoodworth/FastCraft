package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModelFactory
import net.benwoodworth.fastcraft.crafting.presenter.FastCraftGuiPresenter
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiViewFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraftGuiFactory @Inject constructor(
    private val guiFactory: FcGuiFactory,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val itemTypes: FcItemTypes,
    private val fastCraftGuiModelFactory: FastCraftGuiModelFactory,
    private val fastCraftGuiViewFactory: FastCraftGuiViewFactory
) {
    fun openFastCraftGui(player: FcPlayer) {
        val model = fastCraftGuiModelFactory.create(player)
        val view = fastCraftGuiViewFactory.create(player)

        FastCraftGuiPresenter(
            player,
            model,
            view,
            textFactory,
            textColors,
            itemTypes
        )
    }
}
