package net.benwoodworth.fastcraft.sponge

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.FastCraftModule
import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry
import net.benwoodworth.fastcraft.sponge.dependencies.event.SpongeEventListenerRegistry
import javax.inject.Singleton

/**
 * Created by ben on 6/5/17.
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
    override fun eventListeners(): FcEventListenerRegistry {
        return SpongeEventListenerRegistry(fastCraft)
    }
}
