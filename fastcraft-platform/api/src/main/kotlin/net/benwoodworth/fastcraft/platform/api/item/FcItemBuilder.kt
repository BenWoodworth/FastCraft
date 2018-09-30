package net.benwoodworth.fastcraft.platform.api.item

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilder {

    /**
     * Set the item type.
     */
    fun type(type: FcItemType): FcItemBuilderTyped

    /**
     * Set the item type.
     */
    fun type(type: FcItemTypes.() -> FcItemType): FcItemBuilderTyped

    /**
     * Build an item based off this [item].
     */
    fun from(item: FcItem): FcItemBuilderTyped
}
