package net.benwoodworth.fastcraft.platform.bukkit.item

import net.benwoodworth.fastcraft.platform.api.item.FcItemTypes
import org.bukkit.Material
import javax.inject.Inject

class BukkitFcItemTypes @Inject constructor(
) : FcItemTypes {

    override fun getIronSword() = BukkitFcItemType(Material.IRON_SWORD)

    override fun getCraftingTable() = BukkitFcItemType(Material.WORKBENCH)

    override fun getAnvil() = BukkitFcItemType(Material.ANVIL)

    override fun getNetherStar() = BukkitFcItemType(Material.NETHER_STAR)
}
