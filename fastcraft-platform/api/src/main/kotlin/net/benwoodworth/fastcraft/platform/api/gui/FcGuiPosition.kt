package net.benwoodworth.fastcraft.platform.api.gui

import kotlin.math.abs

data class FcGuiPosition(
    val x: Int,
    val y: Int
) : FcGuiRegion.Positioned {

    override val position = this

    override fun contains(position: FcGuiPosition) = this == position

    override fun offset(position: FcGuiPosition) = FcGuiPosition(x + position.x, y + position.y)

    override fun position(position: FcGuiPosition) = position

    /**
     * Creates a new [FcGuiPosition] with negated coordinates.
     */
    operator fun unaryMinus() = FcGuiPosition(-x, -y)

    /**
     * Create a rectangle that encompasses the two locations.
     */
    operator fun rangeTo(position: FcGuiPosition) = FcGuiRegion.Rectangle(
        minOf(x, position.x),
        minOf(y, position.y),
        abs(x - position.x) + 1,
        abs(y - position.y) + 1
    )
}