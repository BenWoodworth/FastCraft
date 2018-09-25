package net.benwoodworth.fastcraft.platform.api.item

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilder {

    /**
     * Build the [FcItem].
     *
     * @return the built [FcItem].
     */
    fun build(): FcItem

    /**
     * Resets this builder and uses the values from this item.
     *
     * @return this builder, for chaining.
     */
    fun from(item: FcItem): FcItemBuilder

    /**
     * Set the item type.
     *
     * @param type the item type.
     * @return this builder, for chaining.
     */
    fun type(type: FcItemType): FcItemBuilder

    /**
     * Set the item amount.
     *
     * @param amount the item amount.
     * @return this builder, for chaining.
     */
    fun amount(amount: Int): FcItemBuilder

    /**
     * Set the item's display name.
     *
     * @param displayName the display name.
     * @return this builder, for chaining.
     */
    fun displayName(displayName: FcText?): FcItemBuilder

    /**
     * Set the item's lore.
     *
     * @param lore the lore.
     * @return this builder, for chaining.
     */
    fun lore(lore: List<FcText?>): FcItemBuilder

    /**
     * Set the item's lore.
     *
     * @param lore the lore.
     * @return this builder, for chaining.
     */
    fun lore(vararg lore: FcText?): FcItemBuilder = lore(lore.toList())

    /**
     * Set the item's durability.
     *
     * @param durability the durability.
     * @return this builder, for chaining.
     */
    fun durability(durability: Int): FcItemBuilder
}