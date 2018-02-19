package net.benwoodworth.fastcraft.dependencies.api.gui.region

interface GuiRegion {

    val location: GuiPoint

    /**
     * Checks if a point is contained within this region.
     */
    operator fun contains(point: GuiPoint): Boolean

    fun union(vararg regions: GuiRegion): GuiRegion {
        return Conditional { point -> (point in this) || regions.any { point in it } }
    }

    operator fun plus(region: GuiRegion) = union(region)

    fun intersect(vararg regions: GuiRegion): GuiRegion {
        return Conditional { point -> (point in this) && regions.all { point in it } }
    }

    operator fun times(region: GuiRegion) = intersect(region)

    fun subtract(vararg regions: GuiRegion): GuiRegion {
        return Conditional { point -> (point in this) && regions.none { point in it } }
    }

    operator fun minus(region: GuiRegion) = subtract(region)

    fun xor(vararg regions: GuiRegion): GuiRegion {
        return Conditional { point -> regions.fold(point in this) { r, it -> r xor (point in it) } }
    }

    operator fun div(region: GuiRegion) = xor(region)

    fun invert(): GuiRegion {
        return Conditional { point -> !contains(point) }
    }

    operator fun not() = invert()

    fun offset(point: GuiPoint): GuiRegion {
        return Conditional { point1 -> contains(point1.offset(point)) }
    }

    fun position(point: GuiPoint): GuiRegion {
        return Conditional { point1 -> contains(point) }
    }

    class Conditional(
            override val location: GuiPoint = GuiPoint(0, 0),
            private val condition: (point: GuiPoint) -> Boolean
    ) : GuiRegion {
        override fun contains(point: GuiPoint) = condition(point.offset(-location))
    }

    class Rectangle(
            override val location: GuiPoint,
            val width: Int,
            val height: Int
    ) : GuiRegion {
        override fun contains(point: GuiPoint) = location.run {
            (point.x in x until x + width) && (point.y in y until y + height)
        }
    }
}