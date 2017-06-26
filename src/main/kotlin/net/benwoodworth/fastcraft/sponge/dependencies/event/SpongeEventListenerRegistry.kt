package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.sponge.dependencies.player.SpongePlayerAdapter
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent
import org.spongepowered.api.event.network.ClientConnectionEvent
import javax.inject.Inject

/**
 * Bukkit implementation of `EventListenerRegistry`.
 */
@Suppress("UNUSED_PARAMETER")
class SpongeEventListenerRegistry @Inject constructor(
        fastCraft: SpongeFastCraft
) : EventListenerRegistry() {

    init {
        Sponge.getEventManager().registerListeners(fastCraft, this)
    }

    @Listener
    fun onPluginEnable(event: GamePreInitializationEvent) {
        pluginEnable.notifyHandlers(object : EventPluginEnable {})
    }

    @Listener
    fun onPluginDisable(event: GameStoppingEvent) {
        pluginDisable.notifyHandlers(object : EventPluginDisable {})
    }

    @Listener
    fun onPlayerJoin(event: ClientConnectionEvent.Join) {
        playerJoin.notifyHandlers(object : EventPlayerJoin { // TODO Create class

            override val player: Player
                get() = SpongePlayerAdapter(event.targetEntity)
        })
    }
}
