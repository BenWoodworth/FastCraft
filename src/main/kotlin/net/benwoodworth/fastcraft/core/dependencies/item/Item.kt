package net.benwoodworth.fastcraft.core.dependencies.item

import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * A Minecraft item.
 */
interface Item {

    /** The amount of items in this stack. */
    var amount: Int

    /** The display name of this item. */
    var displayName: Text?

    /** The item's lore */
    var lore: List<Text?>?

    /** The maximum size of this stack. */
    val maxStackSize: Int

    /**
     * Compare equality of this base item to another, ignoring amount.
     *
     * @param item the [Item] to compare to
     * @return `true` if the items are similar
     */
    fun isSimilar(item: Item): Boolean
}
