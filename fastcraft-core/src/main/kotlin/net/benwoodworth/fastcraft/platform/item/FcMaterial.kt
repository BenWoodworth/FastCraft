package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcMaterial {
    val id: String
    val itemName: FcText
    val blockName: FcText
    val maxAmount: Int
    val craftingRemainingItem: FcMaterial?

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        val air: FcMaterial
        val ironSword: FcMaterial
        val craftingTable: FcMaterial
        val anvil: FcMaterial
        val netherStar: FcMaterial

        fun parseOrNull(id: String): FcMaterial?
    }
}
