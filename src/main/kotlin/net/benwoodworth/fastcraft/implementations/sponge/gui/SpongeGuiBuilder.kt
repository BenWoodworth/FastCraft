package net.benwoodworth.fastcraft.implementations.sponge.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [GuiBuilder].
 */
class SpongeGuiBuilder(
        private val plugin: Any
) : GuiBuilder {

    private var height: Int? = null
    private var title: Sponge_Text? = null

    override fun build(): Gui {
        return SpongeGui(
                plugin,
                height!!,
                title
        )
    }

    override fun height(height: Int): GuiBuilder {
        this.height = height
        return this
    }

    override fun title(title: Text): GuiBuilder {
        this.title = (title as SpongeText).base
        return this
    }
}