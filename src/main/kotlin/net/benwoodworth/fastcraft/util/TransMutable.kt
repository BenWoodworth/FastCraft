package net.benwoodworth.fastcraft.util

/**
 * An interface for implementations with mutable and immutable variants.
 *
 * @param TImmutable the immutable type.
 * @param TMutable the mutable type.
 */
interface TransMutable<out TImmutable, out TMutable : TImmutable> {

    /**
     * Create a mutable copy of this object.
     *
     * @return a mutable copy.
     */
    fun mutableCopy(): TMutable

    /**
     * Create an immutable copy of this object.
     *
     * @return an immutable copy.
     */
    fun immutableCopy(): TImmutable
}
