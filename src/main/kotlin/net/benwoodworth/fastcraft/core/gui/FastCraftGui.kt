package net.benwoodworth.fastcraft.core.gui

import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiLayout
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text
import javax.inject.Inject
import javax.inject.Provider

class FastCraftGui private constructor(
        private val textBuilder: Provider<Text.Builder>,
        itemBuilder: Provider<Item.Builder>,
        guiBuilder: Provider<Gui.Builder>,

        val player: Player
) {

    /**
     * Button for changing pages.
     */
    private val buttonPage: GuiButton = GuiButton().apply {
        item = itemBuilder.get()
                .type("minecraft:iron_sword")
                .amount(1)
                .displayName(textBuilder.get()
                        .text("Page")
                        .build()
                )
                .build()

        clickListener += ::onClickButtonPage
    }

    /**
     * Button to open the vanilla crafting grid.
     */
    private val buttonWorkbench = GuiButton().apply {
        item = itemBuilder.get()
                .type("minecraft:crafting_table")
                .amount(1)
                .displayName(textBuilder.get()
                        .text("Open Crafting Grid") // TODO Localize
                        .build()
                )
                .build()

        clickListener += ::onClickButtonWorkbench
    }

    /**
     * Button to change the crafting multiplier.
     */
    private val buttonMultiplier = GuiButton().apply {
        item = itemBuilder.get()
                .type("minecraft:anvil")
                .amount(1)
                .displayName(textBuilder.get()
                        .text("Multiplier") // TODO Localize
                        .build()
                )
                .build()

        clickListener += ::onClickButtonMultiplier
    }

    /**
     * Button to refresh the recipes.
     */
    private val buttonRefresh = GuiButton().apply {
        item = itemBuilder.get()
                .type("minecraft:nether_star")
                .amount(1)
                .displayName(textBuilder.get()
                        .text("Refresh") // TODO Localize
                        .build()
                )
                .build()

        clickListener += ::onClickButtonRefresh
    }

    /**
     * The [Gui] for the FastCraft interface.
     */
    private val gui = guiBuilder.get()
            .height(6)
            .title(textBuilder.get()
                    .text("FastCraft") // TODO Localize
                    .build()
            )
            .build().apply {

        addLayout(0, 0, layoutRecipes)
        addLayout(8, 0, layoutSidebar)
    }

    /**
     * The layout containing the recipes.
     */
    private val layoutRecipes = GuiLayoutComposite(7, 6)

    /**
     * The layout containing the sidebar buttons.
     */
    private val layoutSidebar = GuiLayout(1, 6).apply {
        setButton(0, 0, buttonWorkbench)
        setButton(0, 2, buttonMultiplier)
        setButton(0, 3, buttonRefresh)
        setButton(0, 5, buttonPage)
    }

    /**
     * Open the FastCraft GUI.
     */
    fun open() = gui.open(player)

    private fun onClickButtonPage() {
        // TODO REMOVE DEBUG CODE

        player.sendMessage(textBuilder.get()
                .text("You clicked the page button!!")
                .build()
        )
    }

    private fun onClickButtonWorkbench() {

    }

    private fun onClickButtonMultiplier() {

    }

    private fun onClickButtonRefresh() {

    }

    class Factory @Inject constructor(
            private val textBuilder: Provider<Text.Builder>,
            private val itemBuilder: Provider<Item.Builder>,
            private val guiBuilder: Provider<Gui.Builder>
    ) {

        fun create(player: Player) = FastCraftGui(
                textBuilder,
                itemBuilder,
                guiBuilder,
                player
        )
    }
}