package net.benwoodworth.fastcraft.dependencies.item

/**
 * A factory for creating item types.
 */
interface ItemTypeFactory {

    fun getIronSword(): ItemType

    fun getCraftingTable(): ItemType

    fun getAnvil(): ItemType

    fun getNetherStar(): ItemType
}