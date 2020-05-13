package net.benwoodworth.fastcraft.crafting.view

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.crafting.view.buttons.*
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcMaterial
import javax.inject.Inject

class FastCraftGuiView(
    player: FcPlayer,
    guiFactory: FcGui.Factory,
    workbenchButtonFactory: WorkbenchButtonView.Factory,
    pageButtonFactory: PageButtonView.Factory,
    recipeButtonFactory: RecipeButtonView.Factory,
    craftAmountButtonFactory: CraftAmountButtonView.Factory,
    refreshButtonFactory: RefreshButtonView.Factory,
    textFactory: FcText.Factory,
    config: FastCraftConfig,
) {
    val gui = guiFactory.createChestGui(
        player = player,
        title = textFactory.createLegacy(Strings.guiTitle(player.locale)),
        height = config.fastCraftUi.height
    )

    init {
        val c = config.fastCraftUi
        val backgroundItem = c.backgroundItem

        for (row in 0 until gui.layout.height) {
            for (col in 0 until gui.layout.width) {
                gui.layout.getButton(col, row).copyItem(backgroundItem)
            }
        }
    }

    val recipeButtons = config.fastCraftUi.recipes.let { c ->
        List(c.width * c.height) { i ->
            recipeButtonFactory.create(
                gui.layout.getButton(
                    column = c.column + i % (c.width),
                    row = c.row + i / (c.width)
                ),
                player.locale
            )
        }
    }

    val workbenchButton = config.fastCraftUi.buttons.craftingGrid.let { c ->
        workbenchButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val craftAmountButton = config.fastCraftUi.buttons.craftAmount.let { c ->
        craftAmountButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val refreshButton = config.fastCraftUi.buttons.refresh.let { c ->
        refreshButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val pageButton = config.fastCraftUi.buttons.page.let { c ->
        pageButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    class Factory @Inject constructor(
        private val guiFactory: FcGui.Factory,
        private val workbenchButtonFactory: WorkbenchButtonView.Factory,
        private val pageButtonFactory: PageButtonView.Factory,
        private val recipeButtonFactory: RecipeButtonView.Factory,
        private val craftAmountButtonFactory: CraftAmountButtonView.Factory,
        private val refreshButtonFactory: RefreshButtonView.Factory,
        private val textFactory: FcText.Factory,
        private val config: FastCraftConfig,
        private val materials: FcMaterial.Factory,
    ) {
        fun create(player: FcPlayer): FastCraftGuiView {
            return FastCraftGuiView(
                player = player,
                guiFactory = guiFactory,
                workbenchButtonFactory = workbenchButtonFactory,
                pageButtonFactory = pageButtonFactory,
                recipeButtonFactory = recipeButtonFactory,
                craftAmountButtonFactory = craftAmountButtonFactory,
                refreshButtonFactory = refreshButtonFactory,
                textFactory = textFactory,
                config = config,
            )
        }
    }
}
