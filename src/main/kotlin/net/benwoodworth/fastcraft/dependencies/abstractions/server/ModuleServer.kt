package net.benwoodworth.fastcraft.dependencies.abstractions.server

/**
 * Dagger module for server dependencies.
 */
interface ModuleServer {

    fun taskBuilder(): Task.Builder

    fun pluginProvider(): Plugin.Provider
}
