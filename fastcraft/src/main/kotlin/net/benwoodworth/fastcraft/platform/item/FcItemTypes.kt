package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.util.Extensible

interface FcItemTypes : Extensible {

    val air: FcItemType

    val ironSword: FcItemType

    val craftingTable: FcItemType

    val anvil: FcItemType

    val netherStar: FcItemType
}
