package net.benwoodworth.fastcraft.core.dependencies.server

/**
 * Dagger module for server dependencies.
 */
interface ModuleServer {

    fun logger(): Logger

    fun taskScheduler(): TaskScheduler

    // fun pluginProvider(): PluginProvider // TODO
}
