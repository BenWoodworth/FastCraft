package net.benwoodworth.fastcraft.impl.sponge.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.ModuleGui

/**
 * Sponge implementation of [ModuleGui].
 */
@Module
class SpongeModuleGui(
        private val plugin: Any
) : ModuleGui {

    @Provides
    override fun guiBuilder(): Gui.Builder {
        return SpongeGui.Builder(plugin)
    }
}
