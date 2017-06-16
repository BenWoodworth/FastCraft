package net.benwoodworth.fastcraft.core

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): FastCraft
}
