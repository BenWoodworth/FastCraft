package net.benwoodworth.fastcraft.dependencies.server

/**
 * Dagger module for server dependencies.
 */
interface ModuleServer {

    fun taskBuilder(): TaskBuilder
}
