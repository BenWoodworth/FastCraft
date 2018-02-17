package net.benwoodworth.fastcraft.dependencies.api.gui.region

class GuiRegionOffset(
        val region: GuiRegion,
        val dx: Int = 0,
        val dy: Int = 0
) : GuiRegion {

    override fun contains(x: Int, y: Int) = region.contains(x - dx, y - dy)
}