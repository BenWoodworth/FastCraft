package net.benwoodworth.fastcraft.sponge.dependencies.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.core.dependencies.gui.ModuleGui

/**
 * Sponge implementation of [ModuleGui].
 */
@Module
class SpongeModuleGui : ModuleGui {

    @Provides
    override fun guiBuilder(): GuiBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
