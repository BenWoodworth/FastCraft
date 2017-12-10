package net.benwoodworth.fastcraft.impl.bukkit.player

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.player.ModulePlayer
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider
import javax.inject.Singleton

/**
 * Bukkit implementation of [ModulePlayer].
 */
@Module
class BukkitModulePlayer : ModulePlayer {

    @Provides @Singleton
    override fun playerProvider(): PlayerProvider {
        return BukkitPlayerProvider()
    }
}
