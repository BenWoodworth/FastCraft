package net.benwoodworth.fastcraft.platform.api.item

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilderTyped {

    /**
     * Set the item amount.
     */
    fun amount(amount: Int): FcItemBuilderTyped

    /**
     * Set the item's display name.
     */
    fun displayName(displayName: FcText?): FcItemBuilderTyped

    /**
     * Set the item's lore.
     */
    fun lore(lore: List<FcText>?): FcItemBuilderTyped

    /**
     * Set the item's durability.
     */
    fun durability(durability: Int): FcItemBuilderTyped

    /**
     * Returns a new [FcItem].
     */
    fun build(): FcItem
}
