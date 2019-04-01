package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class CraftingGuiView(
    player: FcPlayer,
    guiFactory: FcGuiFactory,
    textFactory: FcTextFactory
) {
    val gui = guiFactory.openChestGui(
        player = player,
        title = textFactory.createFcText("FastCraft"),
        height = 6
    )

    val workbenchButton = gui.layout.getButton(8, 0)

    val multiplierButton = gui.layout.getButton(8, 1)

    val refreshButton = gui.layout.getButton(8, 2)

    val pageButton = gui.layout.getButton(8, 5)

    val recipeButtons = List(42) { i ->
        gui.layout.getButton(i % 7, i / 7)
    }
}