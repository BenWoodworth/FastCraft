package net.benwoodworth.fastcraft.impl.sponge.player

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.player.ModulePlayer
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider
import javax.inject.Singleton

/**
 * Sponge implementation of [ModulePlayer].
 */
@Module
class SpongeModulePlayer : ModulePlayer {

    @Provides @Singleton
    override fun playerProvider(): PlayerProvider {
        return SpongePlayerProvider()
    }
}
