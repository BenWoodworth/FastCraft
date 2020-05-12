package net.benwoodworth.fastcraft.crafting.view

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.crafting.view.buttons.*
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
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
    materials: FcMaterial.Factory,
) {
    val gui = guiFactory.createChestGui(
        player = player,
        title = textFactory.createLegacy(Strings.guiTitle(player.locale)),
        height = config.fastCraftUi.height
    )

    init {
        val c = config.fastCraftUi
        val background = materials.parseOrNull(c.background.item)!!

        for (row in 0 until gui.layout.height) {
            for (col in 0 until gui.layout.width) {
                gui.layout.getButton(col, row).setMaterial(background)
            }
        }
    }

    val workbenchButton = config.fastCraftUi.craftingGridButton.let { c ->
        workbenchButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val craftAmountButton = config.fastCraftUi.craftAmountButton.let { c ->
        craftAmountButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val refreshButton = config.fastCraftUi.refreshButton.let { c ->
        refreshButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val pageButton = config.fastCraftUi.pageButton.let { c ->
        pageButtonFactory
            .takeIf { c.enabled }
            ?.create(gui.layout.getButton(c.column, c.row), player.locale)
    }

    val recipeButtons = config.fastCraftUi.recipeButtons.let { c ->
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
                materials = materials,
            )
        }
    }
}
