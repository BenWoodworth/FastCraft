package net.benwoodworth.fastcraft.impl.sponge.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.ModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.dependencies.event.Listener
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
    override fun listenerPlayerJoin(): Listener<EventPlayerJoin> {
        val listener = Listener.Impl<EventPlayerJoin>()

        Sponge.getEventManager().registerListener(fastCraft,
                ClientConnectionEvent.Join::class.java,
                { listener.notifyHandlers(SpongeEventPlayerJoin(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginDisable(): Listener<EventPluginDisable> {
        val listener = Listener.Impl<EventPluginDisable>()

        Sponge.getEventManager().registerListener(fastCraft,
                GameStoppingEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginDisable(it)) }
        )

        return listener
    }

    @Provides @Singleton
    override fun listenerPluginEnable(): Listener<EventPluginEnable> {
        val listener = Listener.Impl<EventPluginEnable>()

        Sponge.getEventManager().registerListener(fastCraft,
                GamePreInitializationEvent::class.java,
                { listener.notifyHandlers(SpongeEventPluginEnable(it)) }
        )

        return listener
    }
}
