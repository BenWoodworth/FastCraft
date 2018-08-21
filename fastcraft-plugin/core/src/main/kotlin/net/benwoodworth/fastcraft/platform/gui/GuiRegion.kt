package net.benwoodworth.fastcraft.dependencies.gui

interface GuiRegion {

    /**
     * Checks if a location is contained within this region.
     */
    operator fun contains(location: GuiLocation): Boolean

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

    fun offset(location: GuiLocation): Positioned {
        return PositionedImpl(this, location)
    }

    fun position(location: GuiLocation): Positioned {
        return PositionedImpl(this, location)
    }

    interface Positioned : GuiRegion {
        val location: GuiLocation
    }

    private class PositionedImpl(
            private val region: GuiRegion,
            override val location: GuiLocation
    ) : Positioned {
        override fun contains(location: GuiLocation) = region.contains(location.offset(-this.location))
        override fun offset(location: GuiLocation) = PositionedImpl(region, this.location.offset(location))
        override fun position(location: GuiLocation) = PositionedImpl(region, location)
    }

    class Dynamic(
            private val condition: (location: GuiLocation) -> Boolean
    ) : GuiRegion {
        override fun contains(location: GuiLocation) = condition(location)
    }

    class Rectangle(
            override val location: GuiLocation,
            val width: Int,
            val height: Int
    ) : Positioned {
        constructor(x: Int, y: Int, width: Int, height: Int) : this(GuiLocation(x, y), width, height)

        override fun contains(location: GuiLocation) = (location.x in 0 until width) && (location.y in 0 until height)
    }
}