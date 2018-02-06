package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import javax.inject.Inject
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [GuiFactory].
 */
class SpongeGuiFactory @Inject constructor(
        private val plugin: SpongeFastCraft
) : GuiFactory {

    private var height: Int? = null
    private var title: Sponge_Text? = null

    override fun build(): Gui {
        return SpongeGui(
                plugin,
                height!!,
                title
        )
    }

    override fun height(height: Int): GuiFactory {
        this.height = height
        return this
    }

    override fun title(title: Text): GuiFactory {
        this.title = (title as SpongeText).base
        return this
    }
}