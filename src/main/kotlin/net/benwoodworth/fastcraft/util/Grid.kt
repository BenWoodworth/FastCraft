package net.benwoodworth.fastcraft.util

/**
 * An immutable grid of values.
 *
 * @param T the type of object contained in the [Grid].
 */
interface Grid<T> : TransMutable<Grid<T>, Grid.Mutable<T>>, Iterable<T> {

    /**
     * The width of the [Grid].
     */
    val width: Int

    /**
     * The height of the [Grid].
     */
    val height: Int

    /**
     * Get a value in the [Grid].
     *
     * @param i the index of the item, going through each row left
     *          to right, starting from the top row going down.
     * @return the value at the given coordinates.
     */
    operator fun get(i: Int): T

    /**
     * Get a value in the [Grid].
     *
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     * @return the value at the given coordinates.
     */
    operator fun get(x: Int, y: Int): T

    /**
     * Iterate through the grid's entries.
     *
     * @param action the action to take on each element.
     */
    fun forEach(action: ((T), x: Int, y: Int) -> Unit)

    /**
     * Create a new grid, with the contents mapped according
     * to the transform function.
     *
     * @param transform the transformation function.
     */
    fun <R> map(transform: (T) -> R): Grid<R>

    /**
     * Creates an iterator that iterates through each row left
     * to right, starting from the top row going down.
     *
     * @return an iterator.
     */
    override fun iterator(): Iterator<T>

    /**
     * Implementation of [Grid].
     *
     * @param width the width of the [Grid].
     * @param height the height of the [Grid].
     * @param init the initializer for the [Grid]'s values.
     */
    class Impl<T>(
            width: Int,
            height: Int,
            init: (x: Int, y: Int) -> T
    ) : Grid<T> by Mutable.Impl(width, height, init)

    /**
     * A mutable [Grid].
     */
    interface Mutable<T> : Grid<T> {

        /**
         * Set a value in the [Grid].
         *
         * @param i the index of the item, going through each row left
         *          to right, starting from the top row going down.
         * @param value the value to set.
         */
        operator fun set(i: Int, value: T)

        /**
         * Get a value in the [Grid].
         *
         * @param x the x-coordinate.
         * @param y the y-coordinate.
         * @param value the value to set.
         */
        operator fun set(x: Int, y: Int, value: T)

        class Impl<T>(
                override val width: Int,
                override val height: Int,
                init: (x: Int, y: Int) -> T
        ) : Mutable<T> {

            /**
             * The contents of the [Grid].
             */
            @Suppress("UNCHECKED_CAST")
            private val contents: Array<T> = Array<Any?>(width * height) {
                init(it.rem(width), it / width)
            } as Array<T>

            override operator fun get(i: Int) = contents[i]

            override operator fun get(x: Int, y: Int) = contents[x + y * width]

            override operator fun set(i: Int, value: T) {
                contents[i] = value
            }

            override operator fun set(x: Int, y: Int, value: T) {
                contents[x + y * width] = value
            }

            override fun forEach(action: ((T), x: Int, y: Int) -> Unit) {
                for (y in 0..width) {
                    for (x in 0..width) {
                        action(this[x, y], x, y)
                    }
                }
            }

            override fun <R> map(transform: (T) -> R): Grid<R> {
                return Impl(width, height, { x, y -> transform(this[x, y]) })
            }

            override fun mutableCopy(): Mutable<T> {
                return Impl(width, height) { x, y -> this[x, y] }
            }

            override fun immutableCopy(): Grid<T> {
                return Grid.Impl(width, height) { x, y -> this[x, y] }
            }

            override fun iterator() = contents.iterator()
        }
    }
}
