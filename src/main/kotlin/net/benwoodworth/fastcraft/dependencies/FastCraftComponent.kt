package net.benwoodworth.fastcraft.dependencies

import net.benwoodworth.fastcraft.core.FastCraft

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): FastCraft
}
