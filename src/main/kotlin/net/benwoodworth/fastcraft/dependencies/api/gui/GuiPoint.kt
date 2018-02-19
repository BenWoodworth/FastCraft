package net.benwoodworth.fastcraft.dependencies.api.gui

data class GuiPoint(
        val x: Int,
        val y: Int
) : GuiRegion.Positioned {

    override val location = this

    override fun contains(point: GuiPoint) = this == point

    override fun offset(point: GuiPoint) = GuiPoint(x + point.x, y + point.y)

    override fun position(point: GuiPoint) = point

    /**
     * Creates a new [GuiPoint] with negated coordinates.
     */
    operator fun unaryMinus() = GuiPoint(-x, -y)
}