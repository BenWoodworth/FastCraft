package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.util.Extensible

/**
 * A builder that creates Minecraft items.
 */
interface FcItemBuilder : Extensible {

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
