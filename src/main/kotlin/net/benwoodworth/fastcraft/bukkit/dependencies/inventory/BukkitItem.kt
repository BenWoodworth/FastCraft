package net.benwoodworth.fastcraft.bukkit.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

/**
 * Wrapper for Bukkit `ItemStack`s.
 */
class BukkitItem(override val base: ItemStack) : FcItem<ItemStack> {

    override var amount: Int
        get() = base.amount
        set(value) {
            base.amount = value
        }

    override var displayName: String?
        get() = base.itemMeta.displayName
        set(value) {
            val meta = base.itemMeta
            meta.displayName = value
            base.itemMeta = meta
        }

    override var lore: List<String?>
        get() = base.itemMeta.lore.toList()
        set(value) {
            val meta = base.itemMeta
            meta.lore = value
            base.itemMeta = meta
        }

    override val maxStackSize: Int
        get() = base.maxStackSize

    override val hasWildCardData: Boolean
        get() = base.durability == Short.MAX_VALUE

    override fun clone(): FcItem<ItemStack> {
        return BukkitItem(base.clone())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        val meta = base.itemMeta
        val enchant = Enchantment.getByName(enchantmentId)
        meta.addEnchant(enchant, level, ignoreLevelRestriction)
        base.itemMeta = meta
    }

    override fun isSimilar(item: FcItem<ItemStack>): Boolean {
        return base.isSimilar(item.base)
    }

    override fun matchesIngredient(ingredient: FcItem<ItemStack>): Boolean {
        var compare = ingredient.base
        if (ingredient.hasWildCardData) {
            compare = compare.clone()
            compare.durability = base.durability
        }
        return base.isSimilar(compare)
    }

    override fun equals(other: Any?) = false

    fun equals(other: FcItem<ItemStack>): Boolean {
        return base == other.base
    }

    override fun hashCode(): Int {
        return base.hashCode()
    }

}
