package net.benwoodworth.fastcraft.platform.gui

import kotlin.math.abs

data class GuiLocation(
        val x: Int,
        val y: Int
) : GuiRegion.Positioned {

    override val location = this

    override fun contains(location: GuiLocation) = this == location

    override fun offset(location: GuiLocation) = GuiLocation(x + location.x, y + location.y)

    override fun position(location: GuiLocation) = location

    /**
     * Creates a new [GuiLocation] with negated coordinates.
     */
    operator fun unaryMinus() = GuiLocation(-x, -y)

    /**
     * Create a rectangle that encompasses the two locations.
     */
    operator fun rangeTo(location: GuiLocation) = GuiRegion.Rectangle(
            minOf(x, location.x),
            minOf(y, location.y),
            abs(x - location.x) + 1,
            abs(y - location.y) + 1
    )
}