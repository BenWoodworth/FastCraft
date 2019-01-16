package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.Material

@Suppress("ClassName")
class BukkitFcItemTypes : FcItemTypes {

    override val air = BukkitFcItemType(Material.AIR)

    override val ironSword = BukkitFcItemType(Material.IRON_SWORD)

    override val craftingTable = BukkitFcItemType(Material.CRAFTING_TABLE)

    override val anvil = BukkitFcItemType(Material.ANVIL)

    override val netherStar = BukkitFcItemType(Material.NETHER_STAR)
}
