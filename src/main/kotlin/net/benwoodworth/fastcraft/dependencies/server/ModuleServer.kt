package net.benwoodworth.fastcraft.dependencies.server

/**
 * Dagger module for server dependencies.
 */
interface ModuleServer {

    fun taskBuilder(): Task.Builder
}
