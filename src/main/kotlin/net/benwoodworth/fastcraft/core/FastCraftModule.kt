package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry

/**
 * Created by ben on 6/4/17.
 */
interface FastCraftModule {

    fun eventListeners(): FcEventListenerRegistry
}
