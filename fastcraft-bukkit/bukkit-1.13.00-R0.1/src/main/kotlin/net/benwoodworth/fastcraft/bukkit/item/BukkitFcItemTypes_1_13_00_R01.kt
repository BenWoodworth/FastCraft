package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.Material

class BukkitFcItemTypes_1_13_00_R01 : BukkitFcItemTypes {

    override val air = BukkitFcItemType_1_13_00_R01(Material.AIR)

    override val ironSword = BukkitFcItemType_1_13_00_R01(Material.IRON_SWORD)

    override val craftingTable = BukkitFcItemType_1_13_00_R01(Material.CRAFTING_TABLE)

    override val anvil = BukkitFcItemType_1_13_00_R01(Material.ANVIL)

    override val netherStar = BukkitFcItemType_1_13_00_R01(Material.NETHER_STAR)

    override fun fromMaterial(material: Material): FcItemType {
        return BukkitFcItemType_1_13_00_R01(material)
    }
}
