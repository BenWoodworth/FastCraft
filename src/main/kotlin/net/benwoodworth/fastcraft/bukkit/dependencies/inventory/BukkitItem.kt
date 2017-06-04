package net.benwoodworth.fastcraft.bukkit.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

/**
 * Wrapper for Bukkit `ItemStack`s.
 */
class BukkitItem(override val baseItem: ItemStack) : FcItem<ItemStack> {

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

    override fun clone(): FcItem<ItemStack> {
        return BukkitItem(baseItem.clone())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        val meta = baseItem.itemMeta
        val enchant = Enchantment.getByName(enchantmentId)
        meta.addEnchant(enchant, level, ignoreLevelRestriction)
        baseItem.itemMeta = meta
    }

    override fun isSimilar(item: FcItem<ItemStack>): Boolean {
        return baseItem.isSimilar(item.baseItem)
    }

    override fun matchesIngredient(ingredient: FcItem<ItemStack>): Boolean {
        var compare = ingredient.baseItem
        if (ingredient.hasWildCardData) {
            compare = compare.clone()
            compare.durability = baseItem.durability
        }
        return baseItem.isSimilar(compare)
    }

    override fun equals(other: Any?) = false

    fun equals(other: FcItem<ItemStack>): Boolean {
        return baseItem == other.baseItem
    }

    override fun hashCode(): Int {
        return baseItem.hashCode()
    }

}
