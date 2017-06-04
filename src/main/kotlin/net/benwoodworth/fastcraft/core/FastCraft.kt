package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import javax.inject.Inject

/**
 * The core class of FastCraft.
 *
 * @param TItem The item type
 */
class FastCraft<TItem : FcItem<*>> @Inject constructor(
        eventListeners: EventListenerRegistry<TItem>
) {



}
