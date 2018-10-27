package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.util.Extensible

/**
 * Minecraft item types.
 */
interface FcItemTypes : Extensible {

    val ironSword: FcItemType

    val craftingTable: FcItemType

    val anvil: FcItemType

    val netherStar: FcItemType
}
