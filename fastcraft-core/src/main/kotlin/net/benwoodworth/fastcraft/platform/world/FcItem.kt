package net.benwoodworth.fastcraft.platform.world

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItem {
    val id: String
    val name: FcText
    val maxAmount: Int
    val craftingRemainingItem: FcItem?

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        val air: FcItem
        val ironSword: FcItem
        val craftingTable: FcItem
        val anvil: FcItem
        val netherStar: FcItem

        fun parseOrNull(id: String): FcItem?
    }
}
