package net.benwoodworth.fastcraft.dependencies.abstractions.player

/**
 * Dagger module for player dependencies.
 */
interface ModulePlayer {

    fun playerProvider(): PlayerProvider
}
