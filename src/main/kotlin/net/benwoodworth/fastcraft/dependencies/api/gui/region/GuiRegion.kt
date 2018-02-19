package net.benwoodworth.fastcraft.dependencies.api.gui.region

interface GuiRegion {

    /**
     * Checks if a point is contained within this region.
     */
    operator fun contains(point: GuiPoint): Boolean

    fun union(region: GuiRegion): GuiRegion

    operator fun plus(region: GuiRegion) = union(region)

    fun intersect(region: GuiRegion): GuiRegion

    operator fun times(region: GuiRegion) = intersect(region)

    fun subtract(region: GuiRegion): GuiRegion

    operator fun minus(region: GuiRegion) = subtract(region)

    fun xor(region: GuiRegion): GuiRegion

    fun negate(): GuiRegion

    operator fun not() = negate()

    fun offset(dx: Int = 0, dy: Int = 0): GuiRegion
}