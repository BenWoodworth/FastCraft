package net.benwoodworth.fastcraft.util

/**
 * A mutable grid of values.
 *
 * @param T the type of object contained in the [Grid]
 */
class Grid<T>(
        /**
         * The width of the [Grid].
         */
        val width: Int,

        /**
         * The height of the [Grid].
         */
        val height: Int,

        /**
         * The initializer for the grid's values.
         */
        init: (x: Int, y: Int) -> T

) : Copyable<Grid<T>>, MutableList<T> by MutableList(width * height, {
    init(it.rem(width), it / width)
}) {

    /**
     * Set a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the value at the given coordinates
     */
    @Suppress("UNCHECKED_CAST")
    operator fun get(x: Int, y: Int): T {
        return this[x + y * width]
    }

    /**
     * Get a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param value the value to set
     */
    @Suppress("UNCHECKED_CAST")
    operator fun set(x: Int, y: Int, value: T) {
        this[x + y * width] = value
    }

    /**
     * Makes a shallow copy of the [Grid].
     *
     * @return a copy of the grid
     */
    @Suppress("UNCHECKED_CAST")
    override fun copy(): Grid<T> {
        return Grid<Any?>(width, height, { x, y -> this[x, y] }) as Grid<T>
    }
}
