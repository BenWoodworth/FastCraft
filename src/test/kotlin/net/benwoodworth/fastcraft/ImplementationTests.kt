package net.benwoodworth.fastcraft

import org.junit.jupiter.api.BeforeEach

/**
 * Contains tests for dependency implementations.
 *
 * @param T The abstract dependency type being tested.
 */
abstract class ImplementationTests<out T : Any> {

    /**
     * Create a new instance of [T].
     */
    abstract fun newTestInstance(): T
}
