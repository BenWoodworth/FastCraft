package net.benwoodworth.fastcraft.util

/**
 * An immutable copy of an object.
 */
class Memento<out T : Copyable<T>> (instance: T) {

    /**
     * The snapshot of the instance.
     */
    private val state = instance.copy()

    /**
     * Get a copy of the memento.
     *
     * @return a copy of the memento
     */
    fun get() = state.copy()

    /**
     * Check for equality of the mementos' states.
     *
     * @return true if [other] is a memento, and has the same state
     */
    @Suppress("UNCHECKED_CAST")
    override fun equals(other: Any?): Boolean {
        return other is Memento<*> && state == other.state
    }

    /**
     * Gets the hash code of this memento's state.
     */
    override fun hashCode() = state.hashCode()
}
