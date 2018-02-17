package net.benwoodworth.fastcraft.api.gui.region

interface GuiRegion {

    /**
     * Checks if a point is contained within this region.
     */
    fun contains(x: Int, y: Int): Boolean
}