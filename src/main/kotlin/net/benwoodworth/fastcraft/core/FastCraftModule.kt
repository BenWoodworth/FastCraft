package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry

/**
 * An interface for providing dependencies.
 */
interface FastCraftModule {

    fun eventListeners(): EventListenerRegistry
}
