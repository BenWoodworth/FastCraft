package net.benwoodworth.fastcraft.bukkit.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.ItemAdapter
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

/**
 * Adapter for Bukkit `ItemStack`s.
 */
class BukkitItemAdapter(
        val baseItem: ItemStack
) : ItemAdapter(baseItem) {

    override var amount: Int
        get() = baseItem.amount
        set(value) {
            baseItem.amount = value
        }

    override var displayName: String?
        get() = baseItem.itemMeta.displayName
        set(value) {
            val meta = baseItem.itemMeta
            meta.displayName = value
            baseItem.itemMeta = meta
        }

    override var lore: List<String?>
        get() = baseItem.itemMeta.lore.toList()
        set(value) {
            val meta = baseItem.itemMeta
            meta.lore = value
            baseItem.itemMeta = meta
        }

    override val maxStackSize: Int
        get() = baseItem.maxStackSize

    override val hasWildCardData: Boolean
        get() = baseItem.durability == Short.MAX_VALUE

    override fun clone(): ItemAdapter {
        return BukkitItemAdapter(baseItem.clone())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        val meta = baseItem.itemMeta
        val enchant = Enchantment.getByName(enchantmentId)
        meta.addEnchant(enchant, level, ignoreLevelRestriction)
        baseItem.itemMeta = meta
    }

    override fun isSimilar(item: ItemAdapter): Boolean {
        return baseItem.isSimilar(item.unwrap())
    }

    override fun matchesIngredient(ingredient: ItemAdapter): Boolean {
        var compare = ingredient.unwrap<ItemStack>()
        if (ingredient.hasWildCardData) {
            compare = compare.clone()
            compare.durability = baseItem.durability
        }
        return baseItem.isSimilar(compare)
    }

    override fun equals(other: Any?) = false

    fun equals(other: BukkitItemAdapter): Boolean {
        return baseItem == other.baseItem
    }

    override fun hashCode(): Int {
        return baseItem.hashCode()
    }

}
