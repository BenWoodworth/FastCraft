package net.benwoodworth.fastcraft.util

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase : Any>(
        val base: TBase
) {

    override fun equals(other: Any?): Boolean {
        return other is Adapter<*> && base == other.base
    }

    override fun hashCode() = base.hashCode()
}
