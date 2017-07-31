package net.benwoodworth.fastcraft.util

/**
 * An interface for implementations with mutable and immutable parts.
 *
 * @param TI the immutable type
 * @param TM the mutable type
 */
interface TransMutable<out TI, out TM : TI> {

    /**
     * Create a mutable copy of this object.
     *
     * @return a mutable copy
     */
    fun mutableCopy(): TM

    /**
     * Create an immutable copy of this object.
     *
     * @return an immutable copy
     */
    fun immutableCopy(): TI
}
