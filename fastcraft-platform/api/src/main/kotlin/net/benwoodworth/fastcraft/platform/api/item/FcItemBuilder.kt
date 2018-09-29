package net.benwoodworth.fastcraft.platform.api.item

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilder {

    /**
     * Set the item type.
     */
    fun type(type: FcItemType): Typed

    /**
     * Set the item type.
     */
    fun type(type: FcItemTypes.() -> FcItemType): Typed

    /**
     * Build an item based off this [item].
     */
    fun from(item: FcItem): FcItemBuilder

    interface Typed {

        /**
         * Returns a new [FcItem].
         */
        fun build(): FcItem

        /**
         * Set the item amount.
         */
        fun amount(amount: Int): FcItemBuilder

        /**
         * Set the item's display name.
         */
        fun displayName(displayName: FcText?): FcItemBuilder

        /**
         * Set the item's lore.
         */
        fun lore(lore: List<FcText?>): FcItemBuilder

        /**
         * Set the item's durability.
         */
        fun durability(durability: Int): FcItemBuilder
    }
}
