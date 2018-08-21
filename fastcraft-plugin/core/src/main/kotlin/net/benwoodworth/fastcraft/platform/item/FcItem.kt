package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.TransMutable

/**
 * An immutable Minecraft item.
 */
interface FcItem : TransMutable<FcItem, FcItem.Mutable> {

    /**
     * The item type.
     */
    val type: FcItemType

    /**
     * The amount of items in this stack.
     */
    val amount: Int

    /**
     * The name of this item.
     */
    val name: FcText

    /**
     * The display name of this item.
     */
    val displayName: FcText?

    /**
     * The item's lore.
     */
    val lore: List<FcText?>?

    /**
     * The maximum size of this stack.
     */
    val maxStackSize: Int

    /**
     * The item's durability.
     */
    val durability: Int

    /**
     * Compare equality of this base item to another, ignoring amount.
     *
     * @param item the [FcItem] to compare to.
     * @return `true` iff the items are similar.
     */
    fun isSimilar(item: FcItem): Boolean

    /**
     * A mutable [FcItem].
     */
    interface Mutable : FcItem {
        override var amount: Int
        override var displayName: FcText?
        override var lore: List<FcText?>?
        override var durability: Int
    }
}
