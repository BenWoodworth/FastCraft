package net.benwoodworth.fastcraft.core.gui

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiLayout
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text
import javax.inject.Inject
import javax.inject.Provider

class FastCraftGui @Inject constructor(
        private val textBuilder: Provider<Text.Builder>,
        private val itemBuilder: Provider<Item.Builder>,

        guiBuilder: Provider<Gui.Builder>
) {

    private val gui: Gui

    private val layoutRecipes: GuiLayoutComposite
    private val layoutSidebar: GuiLayout

    private val buttonPage: GuiButton
    private val buttonWorkbench: GuiButton
    private val buttonMultiplier: GuiButton
    private val buttonRefresh: GuiButton

    init {
        buttonPage = GuiButton.Impl().apply {
            item = itemBuilder.get()
                    .type("minecraft:iron_sword")
                    .amount(1)
                    .displayName(textBuilder.get()
                            .text("Page")
                            .build()
                    )
                    .build()
        }

        buttonWorkbench = GuiButton.Impl().apply {
            item = itemBuilder.get()
                    .type("minecraft:crafting_table")
                    .amount(1)
                    .displayName(textBuilder.get()
                            .text("Open Crafting Grid") // TODO Localize
                            .build()
                    )
                    .build()
        }

        buttonMultiplier = GuiButton.Impl().apply {
            item = itemBuilder.get()
                    .type("minecraft:anvil")
                    .amount(1)
                    .displayName(textBuilder.get()
                            .text("Multiplier") // TODO Localize
                            .build()
                    )
                    .build()
        }

        buttonRefresh = GuiButton.Impl().apply {
            item = itemBuilder.get()
                    .type("minecraft:nether_star")
                    .amount(1)
                    .displayName(textBuilder.get()
                            .text("Refresh") // TODO Localize
                            .build()
                    )
                    .build()
        }

        layoutSidebar = GuiLayout.Impl(1, 6).apply {
            setButton(0, 0, buttonWorkbench)
            setButton(0, 2, buttonWorkbench)
            setButton(0, 3, buttonWorkbench)
            setButton(0, 5, buttonPage)
        }

        layoutRecipes = GuiLayoutComposite.Impl(7, 6)

        gui = guiBuilder.get()
                .height(6)
                .title(textBuilder.get()
                        .text("FastCraft") // TODO Localize
                        .build()
                )
                .build()

        gui.addLayout(0, 0, layoutRecipes)
        gui.addLayout(8, 0, layoutSidebar)

        buttonPage.clickListener += this::onClickButtonPage
        buttonWorkbench.clickListener += this::onClickButtonWorkbench
        buttonMultiplier.clickListener += this::onClickButtonMultiplier
        buttonRefresh.clickListener += this::onClickButtonRefresh
    }

    private fun onClickButtonPage(event: EventGuiButtonClick) {
        // TODO REMOVE DEBUG CODE

        event.player?.sendMessage(textBuilder.get()
                .text("You clicked the page button!!")
                .build()
        )
    }

    private fun onClickButtonWorkbench(event: EventGuiButtonClick) {

    }

    private fun onClickButtonMultiplier(event: EventGuiButtonClick) {

    }

    private fun onClickButtonRefresh(event: EventGuiButtonClick) {

    }

    fun open(player: Player) = gui.open(player)
}