package net.benwoodworth.fastcraft.core.dependencies.util

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter<out TBase>(
        val base: TBase
)
//{
//
//    /**
//     * Unwrap the object being adapted.
//     *
//     * @param T the base item's type
//     * @return the item being adapted
//     * @exception ClassCastException if base item type isn't the type `TBase`
//     */
//    @Suppress("UNCHECKED_CAST")
//    fun unwrap() = base as T
//}
