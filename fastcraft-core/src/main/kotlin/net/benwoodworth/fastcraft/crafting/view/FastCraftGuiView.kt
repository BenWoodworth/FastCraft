package net.benwoodworth.fastcraft.crafting.view

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.crafting.view.buttons.*
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
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

    private val usedButtons: MutableList<FcGuiButton> = mutableListOf()

    private fun getNewButton(column: Int, row: Int): FcGuiButton? {
        return when (val button = gui.layout.getButton(column, row)) {
            in usedButtons -> null
            else -> {
                usedButtons += button
                button
            }
        }
    }

    val workbenchButton = config.fastCraftUi.buttons.craftingGrid.let { c ->
        if (c.enabled) {
            getNewButton(c.column, c.row)
                ?.let { workbenchButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val craftAmountButton = config.fastCraftUi.buttons.craftAmount.let { c ->
        if (c.enabled) {
            getNewButton(c.column, c.row)
                ?.let { craftAmountButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val refreshButton = config.fastCraftUi.buttons.refresh.let { c ->
        if (c.enabled) {
            getNewButton(c.column, c.row)
                ?.let { refreshButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val pageButton = config.fastCraftUi.buttons.page.let { c ->
        if (c.enabled) {
            getNewButton(c.column, c.row)
                ?.let { pageButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val recipeButtons = config.fastCraftUi.recipes.let { c ->
        val buttons = List(c.width * c.height) { i ->
            getNewButton(
                column = c.column + i % (c.width),
                row = c.row + i / (c.width)
            )
        }.filterNotNull()

        buttons.map { recipeButtonFactory.create(it, player.locale) }
    }

    init {
        val c = config.fastCraftUi
        val backgroundItem = c.backgroundItem

        for (row in 0 until gui.layout.height) {
            for (col in 0 until gui.layout.width) {
                getNewButton(col, row)?.copyItem(backgroundItem)
            }
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
        private val items: FcItem.Factory,
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
