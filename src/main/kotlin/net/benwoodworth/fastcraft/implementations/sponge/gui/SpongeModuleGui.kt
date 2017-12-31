package net.benwoodworth.fastcraft.implementations.sponge.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.ModuleGui
import org.spongepowered.api.Sponge

/**
 * Sponge implementation of [ModuleGui].
 */
@Module
class SpongeModuleGui(
        private val plugin: Any
) : ModuleGui {

    init {
        Sponge.getEventManager().registerListeners(plugin, SpongeGui.Listeners())
    }

    @Provides
    override fun guiBuilder(): Gui.Builder {
        return SpongeGui.Builder(plugin)
    }
}
