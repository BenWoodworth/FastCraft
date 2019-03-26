package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import net.benwoodworth.fastcraft.profile

class CraftingGuiView(
    player: FcPlayer,
    guiFactory: FcGuiFactory,
    textFactory: FcTextFactory
) {
    val gui = profile("gui") {
        guiFactory.openChestGui(
            player = player,
            title = textFactory.createFcText("FastCraft"),
            height = 6
        )
    }

    val workbenchButton = profile("workbenchButton") {
        gui.layout.getButton(8, 0)
    }

    val multiplierButton = profile("multiplierButton") {
        gui.layout.getButton(8, 1)
    }

    val refreshButton = profile("refreshButton") {
        gui.layout.getButton(8, 2)
    }

    val pageButton = profile("pageButton") {
        gui.layout.getButton(8, 5)
    }

    val recipeButtons = profile("recipeButtons") {
        List(42) { i ->
            gui.layout.getButton(i % 7, i / 7)
        }
    }
}