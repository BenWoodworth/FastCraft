package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.FastCraft

/**
 * A Dagger chatBaseComponent for injecting FastCraft dependencies.
 */
interface FastCraftComponent {

    fun getFastCraft(): FastCraft
}
