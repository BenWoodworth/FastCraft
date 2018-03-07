package net.benwoodworth.fastcraft.util

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase> {

    /**
     * The base object being adapted.
     */
    abstract val base: TBase

    override fun equals(other: Any?): Boolean {
        return base == (other as? Adapter<*>)?.base
    }

    override fun hashCode() = base?.hashCode() ?: 0
}
