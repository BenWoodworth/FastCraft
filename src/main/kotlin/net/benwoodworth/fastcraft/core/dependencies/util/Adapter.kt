package net.benwoodworth.fastcraft.core.dependencies.util

import net.benwoodworth.fastcraft.sponge.dependencies.text.SpongeTextAdapter

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase>(
        val base: TBase
) {

    override fun equals(other: Any?): Boolean {
        return other is Adapter<*> && base == other.base
    }

    override fun hashCode() = base?.hashCode() ?: 0
}
