package net.benwoodworth.fastcraft.sponge.dependencies.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.event.EventItemCraft
import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.event.ModuleEvent
import net.benwoodworth.fastcraft.core.util.EventListener

/**
 * Sponge implementation of [ModuleEvent].
 */
@Module
class SpongeModuleEvent : ModuleEvent {

    @Provides
    override fun listenerItemCraft(): EventListener<EventItemCraft> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    override fun listenerPlayerJoin(): EventListener<EventPlayerJoin> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    override fun listenerPluginDisable(): EventListener<EventPluginDisable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    override fun listenerPluginEnable(): EventListener<EventPluginEnable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
