package net.benwoodworth.fastcraft.dependencies.sponge.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.abstractions.event.ModuleEvent
import net.benwoodworth.fastcraft.dependencies.abstractions.event.Listener
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent
import org.spongepowered.api.event.network.ClientConnectionEvent
import javax.inject.Singleton

/**
 * Sponge implementation of [ModuleEvent].
 */
@Module
class SpongeModuleEvent(
        private val plugin: Any
) : ModuleEvent {

    @Provides @Singleton
    override fun listenerPlayerJoin(): Listener<EventPlayerJoin> {
        return Listener<EventPlayerJoin>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    ClientConnectionEvent.Join::class.java,
                    { listener.notifyHandlers(SpongeEventPlayerJoin(it)) }
            )
        }
    }

    @Provides @Singleton
    override fun listenerPluginDisable(): Listener<EventPluginDisable> {
        return Listener<EventPluginDisable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameStoppingEvent::class.java,
                    { listener.notifyHandlers(SpongeEventPluginDisable(it)) }
            )
        }
    }

    @Provides @Singleton
    override fun listenerPluginEnable(): Listener<EventPluginEnable> {
        return Listener<EventPluginEnable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameInitializationEvent::class.java,
                    { listener.notifyHandlers(SpongeEventPluginEnable(it)) }
            )
        }
    }
}
