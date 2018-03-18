package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.FcItem
import net.benwoodworth.fastcraft.dependencies.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.FcItemType
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemBuilder @Inject constructor(
        private val itemFactory: BukkitFcItemFactory,
        private val textFactory: BukkitFcText.Factory
) : FcItemBuilder {

    private var result = ItemStack(Material.AIR)

    override fun build(): FcItem = itemFactory.create(result.clone())

    override fun from(item: FcItem) = also {
        result = (item.mutableCopy() as BukkitFcItem.Mutable).base
    }

    override fun type(type: FcItemType) = also {
        result.type = (type as BukkitFcItemType).base
    }

    override fun amount(amount: Int) = also {
        result.amount = amount
    }

    override fun displayName(displayName: FcText?) = also {
        textFactory.setItemDisplayName(result, displayName)
    }

    override fun lore(lore: List<FcText?>) = also {
        textFactory.setItemLore(result, lore)
    }

    override fun durability(durability: Int) = also {
        result.durability = durability.toShort()
    }
}