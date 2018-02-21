package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItemTypeFactory
import org.bukkit.Material
import javax.inject.Inject

class BukkitFcItemTypeFactory @Inject constructor(
) : FcItemTypeFactory {

    override fun getIronSword() = BukkitFcItemType(Material.IRON_SWORD)

    override fun getCraftingTable() = BukkitFcItemType(Material.WORKBENCH)

    override fun getAnvil() = BukkitFcItemType(Material.ANVIL)

    override fun getNetherStar() = BukkitFcItemType(Material.NETHER_STAR)
}