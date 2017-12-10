package net.benwoodworth.fastcraft.dependencies.item

import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.util.TransMutable

/**
 * An immutable Minecraft item.
 */
interface Item : TransMutable<Item, Item.Mutable> {

    /**
     * The amount of items in this stack.
     */
    val amount: Int

    /**
     * The name of this item.
     */
    val name: Text

    /**
     * The display name of this item.
     */
    val displayName: Text?

    /**
     * The item's lore.
     */
    val lore: List<Text?>?

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
     * @param item the [Item] to compare to
     * @return `true` iff the items are similar
     */
    fun isSimilar(item: Item): Boolean

    /**
     * A mutable [Item].
     */
    interface Mutable : Item {

        override var amount: Int

        override var displayName: Text?

        override var lore: List<Text?>?

        override var durability: Int
    }

    /**
     * A builder that creates Minecraft items.
     */
    interface Builder {

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
        fun from(item: Item): Builder

        /**
         * Set the item type.
         *
         * @param typeId the Minecraft item type ID
         * @return this builder, for chaining
         */
        fun type(typeId: String): Builder

        /**
         * Set the item amount.
         *
         * @param amount the item amount
         * @return this builder, for chaining
         */
        fun amount(amount: Int): Builder

        /**
         * Set the item's display name.
         *
         * @param displayName the display name
         * @return this builder, for chaining
         */
        fun displayName(displayName: Text?): Builder

        /**
         * Set the item's lore.
         *
         * @param lore the lore
         * @return this builder, for chaining
         */
        fun lore(vararg lore: Text?): Builder

        /**
         * Set the item's durability.
         *
         * @param durability the durability
         * @return this builder, for chaining
         */
        fun durability(durability: Int)
    }
}
