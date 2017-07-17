package net.benwoodworth.fastcraft.impl.sponge.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.ModuleGui
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft

/**
 * Sponge implementation of [ModuleGui].
 */
@Module
class SpongeModuleGui(
        private val fastCraft: SpongeFastCraft
) : ModuleGui {

    @Provides
    override fun guiBuilder(): Gui.Builder {
        return SpongeGui.Builder(fastCraft)
    }
}
