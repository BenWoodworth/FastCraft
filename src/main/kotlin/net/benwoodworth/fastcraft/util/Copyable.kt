package net.benwoodworth.fastcraft.util

/**
 * An interface for objects that can be copied.
 */
interface Copyable<out T : Any> {

    /**
     * Create a copy of this object.
     *
     * @return a copy of this object
     */
    fun copy(): T
}
