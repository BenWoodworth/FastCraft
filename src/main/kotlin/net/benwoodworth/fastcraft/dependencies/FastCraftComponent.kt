package net.benwoodworth.fastcraft.dependencies

import net.benwoodworth.fastcraft.core.FastCraft

/**
 * A Dagger chatBaseComponent for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): FastCraft
}
