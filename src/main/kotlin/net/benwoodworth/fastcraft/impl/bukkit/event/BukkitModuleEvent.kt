package net.benwoodworth.fastcraft.impl.bukkit.event

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.event.ModuleEvent
import javax.inject.Singleton

/**
 * Bukkit implementation of [ModuleEvent]
 */
@Module
class BukkitModuleEvent : ModuleEvent {

    @Provides @Singleton
    override fun listenerPlayerJoin(): Listener<EventPlayerJoin> {
        TODO("not implemented")
    }

    @Provides @Singleton
    override fun listenerPluginDisable(): Listener<EventPluginDisable> {
        TODO("not implemented")
    }

    @Provides @Singleton
    override fun listenerPluginEnable(): Listener<EventPluginEnable> {
        TODO("not implemented")
    }
}
