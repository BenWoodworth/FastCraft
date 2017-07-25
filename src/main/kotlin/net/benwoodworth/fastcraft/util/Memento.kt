package net.benwoodworth.fastcraft.util

/**
 * An immutable copy of an object.
 */
class Memento<out T : Copyable<T>> (instance: T?) {

    /**
     * The snapshot of the instance.
     */
    private val state = instance?.copy()

    /**
     * Get a copy of the memento.
     *
     * @return a copy of the memento
     */
    fun get() = state?.copy() as T
}
