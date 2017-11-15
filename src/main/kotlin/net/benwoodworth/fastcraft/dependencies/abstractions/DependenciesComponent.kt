package net.benwoodworth.fastcraft.dependencies.abstractions

import net.benwoodworth.fastcraft.core.FastCraft

/**
 * A Dagger component for injecting FastCraft dependencies.
 */
interface DependenciesComponent {

    fun getFastCraft(): FastCraft
}
