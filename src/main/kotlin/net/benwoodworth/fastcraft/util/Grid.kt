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
) : Copyable<Grid<T>>, Iterable<T> {

    /**
     * The contents of the [Grid].
     */
    @Suppress("UNCHECKED_CAST")
    private val contents: Array<T> = Array<Any?>(width * height) {
        init(it.rem(width), it / width)
    } as Array<T>

    /**
     * Set a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the value at the given coordinates
     */
    operator fun get(x: Int, y: Int) = contents[x + y * width]

    /**
     * Get a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param value the value to set
     */
    operator fun set(x: Int, y: Int, value: T) {
        contents[x + y * width] = value
    }

    /**
     * Makes a shallow copy of the [Grid].
     *
     * @return a copy of the grid
     */
    override fun copy() = Grid(width, height, { x, y -> this[x, y] })

    /**
     * Creates an iterator that iterates through each row left
     * to right, starting from the top row going down.
     *
     * @return an iterator
     */
    override fun iterator() = contents.iterator()
}
