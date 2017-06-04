package net.benwoodworth.fastcraft.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.FastCraftComponent
import javax.inject.Singleton

/**
 * Created by ben on 6/4/17.
 */
@Singleton @Component(modules = arrayOf(BukkitFastCraftModule::class))
interface BukkitFastCraftComponent : FastCraftComponent<BukkitItem>
