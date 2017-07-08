package net.benwoodworth.fastcraft.impl.sponge.dependencies.fastcraft

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.fastcraft.ModuleFastCraft
import net.benwoodworth.fastcraft.core.dependencies.server.Plugin
import net.benwoodworth.fastcraft.impl.sponge.dependencies.server.SpongePlugin
import org.spongepowered.api.Sponge
import javax.inject.Named
import javax.inject.Singleton

/**
 * Sponge Implementation of [ModuleFastCraft].
 */
@Module
class SpongeModuleFastCraft : ModuleFastCraft {

    @Provides @Singleton @Named("fastcraft")
    override fun pluginFastCraft(): Plugin {
        return SpongePlugin(
                Sponge.getPluginManager().getPlugin("fastcraft").get()
        )
    }
}
