package net.benwoodworth.fastcraft.impl.sponge.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.gui.GuiLayout
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [GuiBuilder].
 */
class SpongeGuiBuilder (
        private val fastCraft: SpongeFastCraft
) : GuiBuilder {

    private var height: Int? = null
    private var title: Sponge_Text? = null
    private var layout: GuiLayout? = null

    override fun build(): Gui {
        return SpongeGui(
                fastCraft,
                height!!,
                title
        )
    }

    override fun setHeight(height: Int): GuiBuilder {
        this.height = height
        return this
    }

    override fun setTitle(title: Text): GuiBuilder {
        this.title = (title as SpongeText).base
        return this
    }
}
