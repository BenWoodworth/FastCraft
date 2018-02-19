package net.benwoodworth.fastcraft.dependencies.api.gui.region

abstract class GuiRegionAbstract : GuiRegion {

    override fun union(region: GuiRegion): GuiRegion {
        return GuiRegionBasic { point -> (point in this) || (point in region) }
    }

    override fun intersect(region: GuiRegion): GuiRegion {
        return GuiRegionBasic { point -> (point in this) && (point in region) }
    }

    override fun subtract(region: GuiRegion): GuiRegion {
        return GuiRegionBasic { point -> (point in this) && (point !in region) }
    }

    override fun xor(region: GuiRegion): GuiRegion {
        return GuiRegionBasic { point -> (point in this) xor (point in region) }
    }

    override fun negate(): GuiRegion {
        return GuiRegionBasic { point -> !contains(point) }
    }

    override fun offset(dx: Int, dy: Int): GuiRegion {
        return GuiRegionBasic { point -> point.offset(-dx, -dy) in this }
    }
}