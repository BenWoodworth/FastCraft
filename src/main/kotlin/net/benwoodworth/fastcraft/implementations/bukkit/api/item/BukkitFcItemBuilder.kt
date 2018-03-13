package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitFcItemBuilder @Inject constructor(
) : FcItemBuilder {

    private var result = ItemStack(Material.AIR)

    override fun build() = BukkitFcItem(result.clone())

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
        result.itemMeta = result.itemMeta.apply {
            setDisplayName((displayName as BukkitFcText?)?.text)
        }
    }

    override fun lore(lore: List<FcText?>) = also {
        result.itemMeta = result.itemMeta.apply {
            this.lore = lore.map { (it as BukkitFcText?)?.text }
        }
    }

    override fun durability(durability: Int) = also {
        result.durability = durability.toShort()
    }
}