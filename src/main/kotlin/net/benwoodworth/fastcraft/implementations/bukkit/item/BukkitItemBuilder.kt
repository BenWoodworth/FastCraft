package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemType
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitItemBuilder @Inject constructor(
) : ItemBuilder {

    private var result = ItemStack(Material.AIR)

    override fun build() = BukkitItem(result.clone())

    override fun from(item: Item) = also {
        result = (item.mutableCopy() as BukkitItem.Mutable).base
    }

    override fun type(type: ItemType) = also {
        result.type = (type as BukkitItemType).base
    }

    override fun amount(amount: Int) = also {
        result.amount = amount
    }

    override fun displayName(displayName: Text?) = also {
        result.itemMeta = result.itemMeta.apply {
            setDisplayName((displayName as BukkitText?)?.text)
        }
    }

    override fun lore(lore: List<Text?>) = also {
        result.itemMeta = result.itemMeta.apply {
            this.lore = lore.map { (it as BukkitText?)?.text }
        }
    }

    override fun durability(durability: Int) = also {
        result.durability = durability.toShort()
    }
}