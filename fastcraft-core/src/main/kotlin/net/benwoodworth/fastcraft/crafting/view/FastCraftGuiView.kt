package net.benwoodworth.fastcraft.crafting.view

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class FastCraftGuiView(
    player: FcPlayer,
    @Provided guiFactory: FcGui.Factory,
    @Provided workbenchButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.WorkbenchButtonViewFactory,
    @Provided pageButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.PageButtonViewFactory,
    @Provided recipeButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.RecipeButtonViewFactory,
    @Provided craftAmountButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.CraftAmountButtonViewFactory,
    @Provided refreshButtonFactory: net.benwoodworth.fastcraft.crafting.view.buttons.RefreshButtonViewFactory,
    @Provided private val textFactory: FcTextFactory,
) {
    val gui = guiFactory.createChestGui(
        player = player,
        title = textFactory.createLegacy(Strings.guiTitle(player.locale)),
        height = 6
    )

    private val width = gui.layout.width
    private val height = gui.layout.height

    val workbenchButton = workbenchButtonFactory
        .create(gui.layout.getButton(width - 1, 0), player.locale)

    val craftAmountButton = craftAmountButtonFactory
        .create(gui.layout.getButton(width - 1, 1), player.locale)

    val refreshButton = refreshButtonFactory
        .create(gui.layout.getButton(width - 1, 2), player.locale)

    val pageButton = pageButtonFactory
        .create(gui.layout.getButton(width - 1, height - 1), player.locale)

    val recipeButtons = List((width - 2) * height) { i ->
        recipeButtonFactory.create(
            gui.layout.getButton(
                column = i % (width - 2),
                row = i / (width - 2)
            ),
            player.locale
        )
    }
}
