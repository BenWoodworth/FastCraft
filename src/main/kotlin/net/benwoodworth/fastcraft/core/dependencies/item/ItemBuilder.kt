package net.benwoodworth.fastcraft.core.dependencies.item

import net.benwoodworth.fastcraft.core.dependencies.util.Builder

/**
 * A builder that creates Minecraft items.
 */
interface ItemBuilder : Builder<Item> {

    /**
     * Resets this builder and uses the values from this item.
     *
     * @Return This builder, for chaining
     */
    fun from(item: Item): ItemBuilder

    /**
     * Set the item type.
     *
     * @param typeId The Minecraft item type ID
     * @Return This builder, for chaining
     */
    fun setType(typeId: String): ItemBuilder

    /**
     * Set the item amount.
     *
     * @param amount The item amount
     * @Return This builder, for chaining
     */
    fun setAmount(amount: Int): ItemBuilder

    /**
     * Set the item's display name.
     *
     * @param displayName The display name
     * @Return This builder, for chaining
     */
    fun setDisplayName(displayName: String?): ItemBuilder

    /**
     * Set the item's lore.
     *
     * @param lore The lore
     * @Return This builder, for chaining
     */
    fun setLore(vararg lore: List<String?>?): ItemBuilder

    /**
     * Add an enchantment.
     *
     * @param enchantmentId The Minecraft enchantment type ID
     * @param level The enchantment level
     * @Return This builder, for chaining
     */
    fun addEnchantment(enchantmentId: String, level: Int): ItemBuilder
}
