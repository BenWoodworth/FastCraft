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
     * Whether this item, as an ingredient, matches any
     * item of the same type, regardless of data.
     */
    val hasWildCardData: Boolean

    /**
     * Clone this item.
     *
     * @return a clone of this item
     */
    fun clone(): Item

    /**
     * Add an enchantment to the item.
     *
     * @param enchantmentId the Minecraft enchantment ID
     * @param level the enchantment level
     */
    fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean = false)

    /**
     * Compare equality of this base item to another, ignoring amount.
     *
     * @param item the [Item] to compare to
     * @return `true` if the items are similar
     */
    fun isSimilar(item: Item): Boolean

    /**
     * Checks if this item can be used as an ingredient in a recipe.
     * Ingredient can have wildcard data.
     *
     * @param ingredient the ingredient of a recipe
     * @return `true` if this item can be used as the ingredient
     */
    fun matchesIngredient(ingredient: Item): Boolean
}
