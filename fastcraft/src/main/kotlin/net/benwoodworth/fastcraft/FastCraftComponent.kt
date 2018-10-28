package net.benwoodworth.fastcraft

/**
 * A Dagger chatBaseComponent for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): net.benwoodworth.fastcraft.FastCraft
}
