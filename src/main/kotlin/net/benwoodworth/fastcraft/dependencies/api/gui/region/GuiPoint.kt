package net.benwoodworth.fastcraft.dependencies.api.gui.region

import kotlin.math.abs

data class GuiPoint(
        val x: Int,
        val y: Int
) : GuiRegion {

    override val location = this

    override fun contains(point: GuiPoint) = this == point

    override fun offset(point: GuiPoint) = GuiPoint(x + point.x, y + point.y)

    override fun position(point: GuiPoint) = point

    /**
     * Creates a [GuiRegion.Rectangle] encompassing two [GuiPoint]s.
     */
    operator fun rangeTo(point: GuiPoint) = GuiRegion.Rectangle(
            GuiPoint(minOf(x, point.x), minOf(y, point.y)),
            abs(x - point.x) + 1,
            abs(y - point.y) + 1
    )

    /**
     * Creates a new [GuiPoint] with negated coordinates.
     */
    operator fun unaryMinus() = GuiPoint(-x, -y)
}