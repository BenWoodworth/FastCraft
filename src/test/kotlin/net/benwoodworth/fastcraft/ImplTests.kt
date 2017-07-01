package net.benwoodworth.fastcraft

/**
 * Contains tests for dependency implementations.
 *
 * @param T The abstract dependency type being tested.
 */
interface ImplTests<out T : Any> {

    /**
     * Create a new instance of [T].
     */
    fun createInstance(): T
}
