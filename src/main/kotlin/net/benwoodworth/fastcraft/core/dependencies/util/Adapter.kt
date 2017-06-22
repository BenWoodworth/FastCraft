package net.benwoodworth.fastcraft.core.dependencies.util

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase>(
        val base: TBase
) {

    abstract override fun equals(other: Any?): Boolean

    abstract override fun hashCode(): Int
}
