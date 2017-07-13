package net.benwoodworth.fastcraft.core.dependencies.fastcraft

import net.benwoodworth.fastcraft.core.dependencies.server.Plugin

/**
 * Dagger module for FastCraft dependencies.
 */
interface ModuleFastCraft { // TODO Remove?

    fun pluginFastCraft(): Plugin
}
