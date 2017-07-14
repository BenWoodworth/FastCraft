package net.benwoodworth.fastcraft.impl.sponge.player

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.player.ModulePlayer
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider

/**
 * Sponge implementation of [ModulePlayer].
 */
@Module
class SpongeModulePlayer : ModulePlayer {

    @Provides
    override fun playerProvider(): PlayerProvider {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
