package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class CraftingGuiFactory @Inject constructor(
    private val guiFactory: FcGuiFactory,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val itemTypes: FcItemTypes
) {

    fun openFastCraftGui(player: FcPlayer) {
        val model = CraftingGuiModel(player, itemTypes, textFactory)
        val view = CraftingGuiView(player, guiFactory, textFactory, textColors, itemTypes)

        CraftingGuiController(player, model, view, textFactory, textColors, itemTypes)
    }
}