package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.FastCraft

/**
 * A Dagger chatBaseComponent for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): net.benwoodworth.fastcraft.FastCraft
}
