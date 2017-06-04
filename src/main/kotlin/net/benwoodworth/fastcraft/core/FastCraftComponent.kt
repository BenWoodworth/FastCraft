package net.benwoodworth.fastcraft.core

import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import javax.inject.Singleton

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface FastCraftComponent<TFcItem : FcItem<*>> {

    fun getFastCraft(): FastCraft<TFcItem>

    fun eventListeners(): EventListenerRegistry<TFcItem>
}
