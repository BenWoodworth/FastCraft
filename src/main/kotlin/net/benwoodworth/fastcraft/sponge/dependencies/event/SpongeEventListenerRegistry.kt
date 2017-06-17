package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.PlayerJoinEvent
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginDisableEvent
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginEnableEvent
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
        pluginEnable.notifyHandlers(object : PluginEnableEvent {})
    }

    @Listener
    fun onPluginDisable(event: GameStoppingEvent) {
        pluginDisable.notifyHandlers(object : PluginDisableEvent {})
    }

    @Listener
    fun onPlayerJoin(event: ClientConnectionEvent.Join) {
        playerJoin.notifyHandlers(object : PlayerJoinEvent { // TODO Create class

            override val player: Player
                get() = SpongePlayerAdapter(event.targetEntity)
        })
    }
}
