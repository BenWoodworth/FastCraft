package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable
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
 * Bukkit implementation of `FcEventListenerRegistry`.
 */
class SpongeEventListenerRegistry @Inject constructor(
        fastCraft: SpongeFastCraft
) : FcEventListenerRegistry() {

    init {
        Sponge.getEventManager().registerListeners(fastCraft, this)
    }

    @Listener
    fun onPluginEnable(event: GamePreInitializationEvent) {
        pluginEnable.notifyHandlers(object : FcEventPluginEnable {})
    }

    @Listener
    fun onPluginDisable(event: GameStoppingEvent) {
        pluginDisable.notifyHandlers(object : FcEventPluginDisable {})
    }

    @Listener
    fun onPlayerJoin(event: ClientConnectionEvent.Join) {
        playerJoin.notifyHandlers(object : FcEventPlayerJoin { // TODO Create class

            override val player: PlayerAdapter
                get() = SpongePlayerAdapter(event.targetEntity)
        })
    }
}
