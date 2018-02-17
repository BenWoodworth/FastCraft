package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.ItemTypeFactory
import org.bukkit.Material
import javax.inject.Inject

class BukkitItemTypeFactory @Inject constructor(
) : ItemTypeFactory {

    override fun getIronSword() = BukkitItemType(Material.IRON_SWORD)

    override fun getCraftingTable() = BukkitItemType(Material.WORKBENCH)

    override fun getAnvil() = BukkitItemType(Material.ANVIL)

    override fun getNetherStar() = BukkitItemType(Material.NETHER_STAR)
}