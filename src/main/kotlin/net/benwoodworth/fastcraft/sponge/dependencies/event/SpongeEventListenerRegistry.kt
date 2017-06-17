package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.PlayerJoinEventAdapter
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginDisableEventAdapter
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginEnableEventAdapter
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter
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
class SpongeEventListenerRegistry @Inject constructor(
        fastCraft: SpongeFastCraft
) : EventListenerRegistry() {

    init {
        Sponge.getEventManager().registerListeners(fastCraft, this)
    }

    @Listener
    fun onPluginEnable(event: GamePreInitializationEvent) {
        pluginEnable.notifyHandlers(object : PluginEnableEventAdapter(event) {})
    }

    @Listener
    fun onPluginDisable(event: GameStoppingEvent) {
        pluginDisable.notifyHandlers(object : PluginDisableEventAdapter(event) {})
    }

    @Listener
    fun onPlayerJoin(event: ClientConnectionEvent.Join) {
        playerJoin.notifyHandlers(object : PlayerJoinEventAdapter(event) { // TODO Create class

            override val player: PlayerAdapter
                get() = SpongePlayerAdapter(event.targetEntity)
        })
    }
}
