package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A registry of event listeners.
 */
interface EventListenerRegistry<TItem : FcItem<*>> {

    val pluginEnable: FcEventListener<FcEventPluginEnable>
    val pluginDisable: FcEventListener<FcEventPluginDisable>
    val playerJoin: FcEventListener<FcEventPlayerJoin<TItem>>
}
