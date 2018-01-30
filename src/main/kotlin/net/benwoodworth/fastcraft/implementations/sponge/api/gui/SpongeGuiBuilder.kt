package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.api.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import javax.inject.Inject
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [GuiBuilder].
 */
class SpongeGuiBuilder @Inject constructor(
        private val plugin: SpongeFastCraft
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