package net.benwoodworth.fastcraft.api.gui.region

class GuiRegionComposite(
        vararg regions: GuiRegion
) : GuiRegion {

    val regions = regions.toList()

    override fun contains(x: Int, y: Int) = regions.any { it.contains(x, y) }
}