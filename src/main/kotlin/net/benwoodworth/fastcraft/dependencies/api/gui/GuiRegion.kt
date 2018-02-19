package net.benwoodworth.fastcraft.dependencies.api.gui

interface GuiRegion {

    /**
     * Checks if a point is contained within this region.
     */
    operator fun contains(point: GuiPoint): Boolean

    fun union(vararg regions: GuiRegion): GuiRegion {
        return Dynamic { point -> (point in this) || regions.any { point in it } }
    }

    operator fun plus(region: GuiRegion) = union(region)

    fun intersect(vararg regions: GuiRegion): GuiRegion {
        return Dynamic { point -> (point in this) && regions.all { point in it } }
    }

    operator fun times(region: GuiRegion) = intersect(region)

    fun subtract(vararg regions: GuiRegion): GuiRegion {
        return Dynamic { point -> (point in this) && regions.none { point in it } }
    }

    operator fun minus(region: GuiRegion) = subtract(region)

    fun xor(vararg regions: GuiRegion): GuiRegion {
        return Dynamic { point -> regions.fold(point in this) { r, it -> r xor (point in it) } }
    }

    operator fun div(region: GuiRegion) = xor(region)

    fun invert(): GuiRegion {
        return Dynamic { point -> !contains(point) }
    }

    operator fun not() = invert()

    fun offset(point: GuiPoint): Positioned {
        return PositionedImpl(this, point)
    }

    fun position(point: GuiPoint): Positioned {
        return PositionedImpl(this, point)
    }

    interface Positioned : GuiRegion {
        val location: GuiPoint
    }

    private class PositionedImpl(
            private val region: GuiRegion,
            override val location: GuiPoint
    ) : Positioned {
        override fun contains(point: GuiPoint) = region.contains(point.offset(-location))
        override fun offset(point: GuiPoint) = PositionedImpl(region, location.offset(point))
        override fun position(point: GuiPoint) = PositionedImpl(region, point)
    }

    class Dynamic(
            private val condition: (point: GuiPoint) -> Boolean
    ) : GuiRegion {
        override fun contains(point: GuiPoint) = condition(point)
    }

    class Rectangle(
            override val location: GuiPoint,
            val width: Int,
            val height: Int
    ) : Positioned {
        constructor(x: Int, y: Int, width: Int, height: Int) : this(GuiPoint(x, y), width, height)
        override fun contains(point: GuiPoint) = (point.x in 0 until width) && (point.y in 0 until height)
    }
}