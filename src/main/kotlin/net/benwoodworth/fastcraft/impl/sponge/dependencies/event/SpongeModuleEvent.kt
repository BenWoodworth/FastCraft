package net.benwoodworth.fastcraft.impl.sponge.dependencies.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.event.ModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.EventListener
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
        private val fastCraft: SpongeFastCraft
) : ModuleEvent {

    @Provides @Singleton
    override fun listenerPlayerJoin(): EventListener<EventPlayerJoin> {
        val listener = EventListener<EventPlayerJoin>()

        Sponge.getEventManager().registerListener(fastCraft,
                ClientConnectionEvent.Join::class.java,
                { listener.notifyHandlers(SpongeEventPlayerJoin(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginDisable(): EventListener<EventPluginDisable> {
        val listener = EventListener<EventPluginDisable>()

        Sponge.getEventManager().registerListener(fastCraft,
                GameStoppingEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginDisable(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginEnable(): EventListener<EventPluginEnable> {
        val listener = EventListener<EventPluginEnable>()

        Sponge.getEventManager().registerListener(fastCraft,
                GamePreInitializationEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginEnable(it)) }
        )

        return listener
    }
}
