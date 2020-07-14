package net.benwoodworth.fastcraft.platform.world

import net.benwoodworth.fastcraft.platform.text.FcText

inline class FcItem(val value: Any) { // TODO inline (causes bug in Kotlin 1.4-M3)
    interface TypeClass {
        val FcItem.id: String
        val FcItem.name: FcText
        val FcItem.maxAmount: Int
        val FcItem.craftingRemainingItem: FcItem?
    }

    interface Factory {
        val air: FcItem
        val ironSword: FcItem
        val craftingTable: FcItem
        val anvil: FcItem
        val netherStar: FcItem
        val lightGrayStainedGlassPane: FcItem

        fun parseOrNull(id: String): FcItem?
    }
}
