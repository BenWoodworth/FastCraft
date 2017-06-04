package net.benwoodworth.fastcraft.bukkit

import dagger.Component
import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.dependencies.event.BukkitEventListenerRegistry
import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.FastCraftComponent
import net.benwoodworth.fastcraft.core.FastCraftModule
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import javax.inject.Singleton

/**
 * The Bukkit implementation of FastCraft.
 */
class BukkitFastCraft {

    @Component(modules = arrayOf(BukkitFastCraftModule::class))
    interface BukkitFastCraftComponent : FastCraftComponent<BukkitItem>

    @Module
    class BukkitFastCraftModule : FastCraftModule<BukkitItem> {

        @Provides @Singleton
        override fun eventListeners(): EventListenerRegistry<BukkitItem> {
            return BukkitEventListenerRegistry()
        }
    }
}
