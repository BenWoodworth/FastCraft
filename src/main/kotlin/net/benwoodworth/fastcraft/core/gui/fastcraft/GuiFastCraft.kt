package net.benwoodworth.fastcraft.core.gui.fastcraft

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.gui.*
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.TextBuilder
import javax.inject.Provider

@AutoFactory
class GuiFastCraft constructor(
        val player: Player,

        @Provided textBuilder: Provider<TextBuilder>,
        @Provided guiBuilder: Provider<GuiBuilder>,

        @Provided buttonMultiplierFactory: GuiButtonMultiplierFactory
) {

    private val buttonPage: GuiButton
        get() = TODO()

    private val buttonWorkbench: GuiButton
        get() = TODO()

    private val buttonMultiplier = buttonMultiplierFactory.create(this)

    private val buttonRefresh: GuiButton
        get() = TODO()

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
     * Open the FastCraft GUI.
     */
    fun open() = gui.open(player)
}