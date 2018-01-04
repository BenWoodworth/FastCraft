package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory
import org.bukkit.Material
import javax.inject.Inject

class BukkitItemTypeFactory @Inject constructor(
) : ItemTypeFactory {

    override fun getIronSword() = BukkitItemType(Material.IRON_SWORD)

    override fun getCraftingTable() = BukkitItemType(Material.WORKBENCH)

    override fun getAnvil() = BukkitItemType(Material.ANVIL)

    override fun getNetherStar() = BukkitItemType(Material.NETHER_STAR)
}