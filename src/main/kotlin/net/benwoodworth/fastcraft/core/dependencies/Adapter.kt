package net.benwoodworth.fastcraft.core.dependencies

/**
 * Used to adapt native implementations for use by FastCraft.
 */
abstract class Adapter(protected val base: Any) {

    /**
     * Unwrap the object being adapted.
     *
     * @param T the base item's type
     * @return the item being adapted
     * @exception ClassCastException if base item type isn't the type `TBase`
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> unwrap() = base as T
}
