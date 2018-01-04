package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.FastCraftComponent
import javax.inject.Singleton

/**
 * Bukkit implementation of [Component].
 */
@Singleton
@Component(modules = [
    BukkitFastCraftModule::class
])
interface BukkitFastCraftComponent : FastCraftComponent
