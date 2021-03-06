package net.benwoodworth.fastcraft.crafting.view

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.crafting.view.buttons.*
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcServer
import net.benwoodworth.fastcraft.platform.text.FcText
import javax.inject.Inject

class FastCraftGuiView(
    player: FcPlayer,
    fcGuiFactory: FcGui.Factory,
    workbenchButtonFactory: WorkbenchButtonView.Factory,
    pageButtonFactory: PageButtonView.Factory,
    recipeButtonFactory: RecipeButtonView.Factory,
    craftAmountButtonFactory: CraftAmountButtonView.Factory,
    refreshButtonFactory: RefreshButtonView.Factory,
    customButtonViewFactory: CustomButtonView.Factory,
    fcTextFactory: FcText.Factory,
    config: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
    private val fcServer: FcServer,
) : FcPlayer.Operations by fcPlayerOperations {
    val gui = fcGuiFactory.createChestGui(
        player = player,
        title = fcTextFactory.createLegacy(Strings.guiTitle(player.locale)),
        height = config.layout.height
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

    val craftingGridButton = config.layout.buttons.craftingGrid.let { c ->
        if (c.enable) {
            getNewButton(c.column, c.row)
                ?.let { workbenchButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val craftAmountButton = config.layout.buttons.craftAmount.let { c ->
        if (c.enable) {
            getNewButton(c.column, c.row)
                ?.let { craftAmountButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val refreshButton = config.layout.buttons.refresh.let { c ->
        if (c.enable) {
            getNewButton(c.column, c.row)
                ?.let { refreshButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val pageButton = config.layout.buttons.page.let { c ->
        if (c.enable) {
            getNewButton(c.column, c.row)
                ?.let { pageButtonFactory.create(it, player.locale) }
        } else {
            null
        }
    }

    val customButtons = config.layout.customButtons.buttons
        .filter { it.enable }
        .flatMap { customButton ->
            customButton.run { row until row + height }.asSequence().flatMap { row ->
                customButton.run { column until column + width }.asSequence().mapNotNull { column ->
                    getNewButton(column, row)
                        ?.apply {
                            copyItem(customButton.item)
                        }
                        ?.let { newButton ->
                            customButtonViewFactory.create(
                                button = newButton,
                                command = customButton.command,
                            )
                        }
                }
            }
        }

    val recipeButtons = config.layout.recipes.let { c ->
        val buttons = List(c.width * c.height) { i ->
            getNewButton(
                column = c.column + i % (c.width),
                row = c.row + i / (c.width)
            )
        }.filterNotNull()

        if (buttons.isEmpty()) {
            error("FastCraft UI does not have space for recipes. Please re-configure the layout.")
        }

        buttons.map { recipeButtonFactory.create(it, player.locale) }
    }

    init {
        val background = config.layout.background
        if (background.enable) {
            val item = background.item

            for (row in 0 until gui.layout.height) {
                for (col in 0 until gui.layout.width) {
                    getNewButton(col, row)?.copyItem(item)
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val fcGuiFactory: FcGui.Factory,
        private val workbenchButtonViewFactory: WorkbenchButtonView.Factory,
        private val pageButtonFactory: PageButtonView.Factory,
        private val recipeButtonFactory: RecipeButtonView.Factory,
        private val craftAmountButtonFactory: CraftAmountButtonView.Factory,
        private val refreshButtonFactory: RefreshButtonView.Factory,
        private val customButtonViewFactory: CustomButtonView.Factory,
        private val fcTextFactory: FcText.Factory,
        private val fastCraftConfig: FastCraftConfig,
        private val fcPlayerOperations: FcPlayer.Operations,
        private val fcServer: FcServer,
    ) {
        fun create(player: FcPlayer): FastCraftGuiView {
            return FastCraftGuiView(
                player = player,
                fcGuiFactory = fcGuiFactory,
                workbenchButtonFactory = workbenchButtonViewFactory,
                pageButtonFactory = pageButtonFactory,
                recipeButtonFactory = recipeButtonFactory,
                craftAmountButtonFactory = craftAmountButtonFactory,
                refreshButtonFactory = refreshButtonFactory,
                customButtonViewFactory = customButtonViewFactory,
                fcTextFactory = fcTextFactory,
                config = fastCraftConfig,
                fcPlayerOperations = fcPlayerOperations,
                fcServer = fcServer,
            )
        }
    }
}
