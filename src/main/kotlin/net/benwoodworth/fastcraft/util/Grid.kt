package net.benwoodworth.fastcraft.util

/**
 * A mutable grid of values.
 *
 * @param T the type of object contained in the [Grid]
 */
interface Grid<T> : Copyable<Grid<T>>, Iterable<T> {

    /**
     * The width of the [Grid].
     */
    val width: Int

    /**
     * The height of the [Grid].
     */
    val height: Int

    /**
     * Set a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the value at the given coordinates
     */
    operator fun get(x: Int, y: Int): T

    /**
     * Get a value in the [Grid].
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param value the value to set
     */
    operator fun set(x: Int, y: Int, value: T)

    /**
     * Makes a shallow copy of the [Grid].
     *
     * @return a copy of the grid
     */
    override fun copy(): Grid<T>

    /**
     * Creates an iterator that iterates through each row left
     * to right, starting from the top row going down.
     *
     * @return an iterator
     */
    override fun iterator(): Iterator<T>

    /**
     * Implementation of [Grid].
     *
     * @param width the width of the [Grid]
     * @param height the height of the [Grid]
     * @param init the initializer for the [Grid]'s values
     */
    class Impl<T>(
            override val width: Int,
            override val height: Int,
            init: (x: Int, y: Int) -> T
    ) : Grid<T> {

        /**
         * The contents of the [Grid].
         */
        @Suppress("UNCHECKED_CAST")
        private val contents: Array<T> = Array<Any?>(width * height) {
            init(it.rem(width), it / width)
        } as Array<T>

        override operator fun get(x: Int, y: Int) = contents[x + y * width]

        override operator fun set(x: Int, y: Int, value: T) {
            contents[x + y * width] = value
        }

        override fun copy() = Grid.Impl(width, height, { x, y -> this[x, y] })

        override fun iterator() = contents.iterator()
    }
}
