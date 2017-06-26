package net.benwoodworth.fastcraft.core.dependencies.util

/**
 * An interface for builders.
 */
interface Builder<out T : Any> {

    /**
     * Build the object.
     *
     * @return the built object
     */
    fun build(): T
}
