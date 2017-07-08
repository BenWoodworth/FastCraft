package net.benwoodworth.fastcraft.impl.sponge.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.Gui
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiLayout
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.dependencies.text.SpongeText
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
                title,
                layout!!
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

    override fun setLayout(layout: GuiLayout): GuiBuilder {
        this.layout = layout
        return this
    }
}
