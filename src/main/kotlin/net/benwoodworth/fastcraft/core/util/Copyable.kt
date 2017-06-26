package net.benwoodworth.fastcraft.core.util

/**
 * An interface for objects that can be copied.
 */
interface Copyable<out T> {

    /**
     * Create a copy of this object.
     *
     * @return a copy of this object
     */
    fun copy(): T
}
