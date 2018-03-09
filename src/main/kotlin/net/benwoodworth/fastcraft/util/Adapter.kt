package net.benwoodworth.fastcraft.util

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase : Any> {

    /**
     * The base object being adapted.
     */
    abstract val base: TBase

    /**
     * Checks equality of the [base] object with that of another [Adapter].
     */
    override fun equals(other: Any?): Boolean {
        return other is Adapter<*> && base == other.base
    }

    /**
     * Calculates the hash code of the base object.
     */
    override fun hashCode() = base.hashCode()
}
