package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface FastCraftComponent<TItem : FcItem<*>> {

    fun getFastCraft(): FastCraft<TItem>
}
