package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.dependencies.event.BukkitEventListenerRegistry
import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.FastCraftModule
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import javax.inject.Singleton

/**
 * Created by ben on 6/4/17.
 */
@Module
class BukkitFastCraftModule(
        private val fastCraft: BukkitFastCraft
) : FastCraftModule<BukkitItem> {

    @Provides @Singleton
    fun fastCraft(): BukkitFastCraft {
        return fastCraft
    }

    @Provides @Singleton
    override fun eventListeners(): EventListenerRegistry<BukkitItem> {
        return BukkitEventListenerRegistry(fastCraft)
    }
}
