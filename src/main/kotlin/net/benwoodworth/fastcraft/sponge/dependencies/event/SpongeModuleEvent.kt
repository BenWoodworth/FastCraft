package net.benwoodworth.fastcraft.sponge.dependencies.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.event.ModuleEvent
import net.benwoodworth.fastcraft.core.util.EventListener
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
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
    override fun listenerPlayerJoin(): EventListener<EventPlayerJoin> {
        val listener = EventListener<EventPlayerJoin>()

        Sponge.getEventManager().registerListener(plugin,
                ClientConnectionEvent.Join::class.java,
                { listener.notifyHandlers(SpongeEventPlayerJoinAdapter(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginDisable(): EventListener<EventPluginDisable> {
        val listener = EventListener<EventPluginDisable>()

        Sponge.getEventManager().registerListener(plugin,
                GameStoppingEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginDisableAdapter(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginEnable(): EventListener<EventPluginEnable> {
        val listener = EventListener<EventPluginEnable>()

        Sponge.getEventManager().registerListener(plugin,
                GamePreInitializationEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginEnableAdapter(it)) }
        )

        return listener
    }
}
