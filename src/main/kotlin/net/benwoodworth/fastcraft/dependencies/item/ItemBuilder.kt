package net.benwoodworth.fastcraft.dependencies.item

import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A builder that creates Minecraft items.
 */
interface ItemBuilder {

    /**
     * Build the [Item].
     *
     * @return the built [Item]
     */
    fun build(): Item

    /**
     * Resets this builder and uses the values from this item.
     *
     * @return this builder, for chaining
     */
    fun from(item: Item): ItemBuilder

    /**
     * Set the item type.
     *
     * @param typeId the Minecraft item type ID
     * @return this builder, for chaining
     */
    fun type(typeId: String): ItemBuilder

    /**
     * Set the item amount.
     *
     * @param amount the item amount
     * @return this builder, for chaining
     */
    fun amount(amount: Int): ItemBuilder

    /**
     * Set the item's display name.
     *
     * @param displayName the display name
     * @return this builder, for chaining
     */
    fun displayName(displayName: Text?): ItemBuilder

    /**
     * Set the item's lore.
     *
     * @param lore the lore
     * @return this builder, for chaining
     */
    fun lore(vararg lore: Text?): ItemBuilder

    /**
     * Set the item's durability.
     *
     * @param durability the durability
     * @return this builder, for chaining
     */
    fun durability(durability: Int)
}