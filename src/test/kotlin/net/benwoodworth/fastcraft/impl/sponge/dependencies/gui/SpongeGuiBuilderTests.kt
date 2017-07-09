package net.benwoodworth.fastcraft.impl.sponge.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.GuiBuilderTests
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.dependencies.text.SpongeTextBuilder
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Tests for [SpongeGuiBuilder].
 */
class SpongeGuiBuilderTests : GuiBuilderTests() {

    override fun createTextBuilder() = SpongeTextBuilder(Sponge_Text.builder(""))

    override fun createInstance() = SpongeGuiBuilder(SpongeFastCraft())
}
