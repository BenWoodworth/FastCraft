package net.benwoodworth.fastcraft.dependencies.player

/**
 * Dagger module for player dependencies.
 */
interface ModulePlayer {

    fun playerProvider(): PlayerProvider
}
