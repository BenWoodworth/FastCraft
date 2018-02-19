package net.benwoodworth.fastcraft.dependencies.api.gui.region

data class GuiPoint(
        val x: Int,
        val y: Int
) : GuiRegion, GuiRegionAbstract() {

    override fun contains(point: GuiPoint) = x == point.x && y == point.y

    override fun offset(dx: Int, dy: Int) = GuiPoint(x + dx, y + dy)
}