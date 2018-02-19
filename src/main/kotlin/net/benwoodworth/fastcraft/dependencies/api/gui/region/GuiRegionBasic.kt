package net.benwoodworth.fastcraft.dependencies.api.gui.region

class GuiRegionBasic(
        private val condition: (point: GuiPoint) -> Boolean
) : GuiRegionAbstract() {

    override fun contains(point: GuiPoint) = condition(point)
}