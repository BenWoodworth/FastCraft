package net.benwoodworth.fastcraft.crafting.view

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class FastCraftGuiView(
    player: FcPlayer,
    @Provided guiFactory: FcGuiFactory,
    @Provided workbenchButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.WorkbenchButtonViewFactory,
    @Provided pageButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.PageButtonViewFactory,
    @Provided recipeButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.RecipeButtonViewFactory,
    @Provided craftAmountButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.CraftAmountButtonViewFactory,
    @Provided refreshButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.RefreshButtonViewFactory,
    @Provided private val textFactory: FcTextFactory
) {
    val gui = guiFactory.createChestGui(
        player = player,
        title = textFactory.createFcText("FastCraft"),
        height = 6
    )

    private val width = gui.layout.width
    private val height = gui.layout.height

    val workbenchButton = workbenchButtonFactory
        .create(gui.layout.getButton(width - 1, 0))

    val craftAmountButton = craftAmountButtonFactory
        .create(gui.layout.getButton(width - 1, 1))

    val refreshButton = refreshButtonFactory
        .create(gui.layout.getButton(width - 1, 2))

    val pageButton = pageButtonFactory
        .create(gui.layout.getButton(width - 1, height - 1))

    val recipeButtons = List((width - 2) * height) { i ->
        recipeButtonFactory.create(
            gui.layout.getButton(
                column = i % (width - 2),
                row = i / (width - 2)
            )
        )
    }
}
