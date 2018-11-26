package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.Material
import javax.inject.Inject

@Suppress("ClassName")
class Bukkit_11300R01_FcItemTypes @Inject constructor(
    itemTypeFactory: Bukkit_11300R01_FcItemTypeFactory
) : FcItemTypes {

    override val air = itemTypeFactory.create(Material.AIR)

    override val ironSword = itemTypeFactory.create(Material.IRON_SWORD)

    override val craftingTable = itemTypeFactory.create(Material.CRAFTING_TABLE)

    override val anvil = itemTypeFactory.create(Material.ANVIL)

    override val netherStar = itemTypeFactory.create(Material.NETHER_STAR)
}
