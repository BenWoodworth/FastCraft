package net.benwoodworth.fastcraft.util

/**
 * Used to adapt native impl for use by FastCraft.
 */
abstract class Adapter<out TBase : Any>(
        val base: TBase
) {

    override fun equals(other: Any?): Boolean {
        return base == (other as? Adapter<*>)?.base
    }

    override fun hashCode() = base.hashCode()
}
