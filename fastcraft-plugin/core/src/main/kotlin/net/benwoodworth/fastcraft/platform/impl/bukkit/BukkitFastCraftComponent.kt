package net.benwoodworth.fastcraft.platform.impl.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.platform.FastCraftComponent
import javax.inject.Singleton

/**
 * Bukkit implementation of [Component].
 */
@Singleton
@Component(modules = [
    BukkitFastCraftModule::class
])
interface BukkitFastCraftComponent : FastCraftComponent
