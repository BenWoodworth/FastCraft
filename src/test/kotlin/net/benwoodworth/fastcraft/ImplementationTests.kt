package net.benwoodworth.fastcraft

import org.junit.jupiter.api.BeforeEach

/**
 * Contains tests for dependency implementations.
 *
 * @param T The abstract dependency type being tested.
 */
abstract class ImplementationTests<T : Any> {

    /**
     * An instance reset before each test.
     */
    lateinit var testInstance: T
        private set

    /**
     * Create a new instance of [T].
     */
    abstract fun createInstance(): T

    @BeforeEach
    fun beforeEach() {
        testInstance = createInstance()
    }
}
