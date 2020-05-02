package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItemType {
    val id: String
    val itemName: FcText
    val blockName: FcText
    val maxAmount: Int
    val craftingResult: FcItemType?

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        val air: FcItemType
        val ironSword: FcItemType
        val craftingTable: FcItemType
        val anvil: FcItemType
        val netherStar: FcItemType
    }
}
