package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.dependencies.event.BukkitEventListenerRegistry
import net.benwoodworth.fastcraft.core.FastCraftModule
import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry

/**
 * Created by ben on 6/4/17.
 */
//@Module
class BukkitFastCraftModule(
        private val fastCraft: BukkitFastCraft
) : FastCraftModule {

    //@Provides @Singleton
    fun fastCraft(): BukkitFastCraft {
        return fastCraft
    }

    //@Provides @Singleton
    override fun eventListeners(): FcEventListenerRegistry {
        return BukkitEventListenerRegistry(fastCraft)
    }
}
