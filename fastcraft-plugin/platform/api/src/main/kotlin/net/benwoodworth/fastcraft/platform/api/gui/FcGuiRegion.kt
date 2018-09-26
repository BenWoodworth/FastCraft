package net.benwoodworth.fastcraft.platform.api.gui

interface FcGuiRegion {

    /**
     * Checks if a position is contained within this region.
     */
    operator fun contains(position: FcGuiPosition): Boolean

    fun union(vararg regions: FcGuiRegion): FcGuiRegion {
        return Dynamic { point -> (point in this) || regions.any { point in it } }
    }

    operator fun plus(region: FcGuiRegion) = union(region)

    fun intersect(vararg regions: FcGuiRegion): FcGuiRegion {
        return Dynamic { point -> (point in this) && regions.all { point in it } }
    }

    operator fun times(region: FcGuiRegion) = intersect(region)

    fun subtract(vararg regions: FcGuiRegion): FcGuiRegion {
        return Dynamic { point -> (point in this) && regions.none { point in it } }
    }

    operator fun minus(region: FcGuiRegion) = subtract(region)

    fun xor(vararg regions: FcGuiRegion): FcGuiRegion {
        return Dynamic { point -> regions.fold(point in this) { r, it -> r xor (point in it) } }
    }

    operator fun div(region: FcGuiRegion) = xor(region)

    fun invert(): FcGuiRegion {
        return Dynamic { point -> !contains(point) }
    }

    operator fun not() = invert()

    fun offset(position: FcGuiPosition): Positioned {
        return PositionedImpl(this, position)
    }

    fun position(position: FcGuiPosition): Positioned {
        return PositionedImpl(this, position)
    }

    interface Positioned : FcGuiRegion {
        val position: FcGuiPosition
    }

    private class PositionedImpl(
            private val region: FcGuiRegion,
            override val position: FcGuiPosition
    ) : Positioned {
        override fun contains(position: FcGuiPosition) = region.contains(position.offset(-this.position))
        override fun offset(position: FcGuiPosition) = PositionedImpl(region, this.position.offset(position))
        override fun position(position: FcGuiPosition) = PositionedImpl(region, position)
    }

    class Dynamic(
            private val condition: (position: FcGuiPosition) -> Boolean
    ) : FcGuiRegion {
        override fun contains(position: FcGuiPosition) = condition(position)
    }

    class Rectangle(
            override val position: FcGuiPosition,
            val width: Int,
            val height: Int
    ) : Positioned {
        constructor(x: Int, y: Int, width: Int, height: Int) : this(FcGuiPosition(x, y), width, height)

        override fun contains(position: FcGuiPosition) = (position.x in 0 until width) && (position.y in 0 until height)
    }
}