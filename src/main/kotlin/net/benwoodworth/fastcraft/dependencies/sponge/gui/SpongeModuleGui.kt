package net.benwoodworth.fastcraft.dependencies.sponge.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.ModuleGui
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
