package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilderTyped : Extensible {

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
