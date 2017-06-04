package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface FastCraftComponent<TFcItem : FcItem<*>> {

    fun getFastCraft(): FastCraft<TFcItem>

    fun eventListeners(): EventListenerRegistry<TFcItem>
}
