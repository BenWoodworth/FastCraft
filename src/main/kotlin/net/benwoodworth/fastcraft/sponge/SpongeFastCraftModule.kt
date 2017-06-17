package net.benwoodworth.fastcraft.sponge

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.FastCraftModule
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.sponge.dependencies.event.SpongeEventListenerRegistry
import javax.inject.Singleton

/**
 * Provides Sponge dependencies.
 */
@Module
class SpongeFastCraftModule(
        private val fastCraft: SpongeFastCraft
) : FastCraftModule {

    @Provides @Singleton
    fun fastCraft(): SpongeFastCraft {
        return fastCraft
    }

    @Provides @Singleton
    override fun eventListeners(): EventListenerRegistry {
        return SpongeEventListenerRegistry(fastCraft)
    }
}
