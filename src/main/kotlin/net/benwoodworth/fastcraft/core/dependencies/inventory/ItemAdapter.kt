package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.util.Adapter

/**
 * A Minecraft item.
 */
abstract class ItemAdapter(baseItem: Any) : Adapter(baseItem) {

    /** The amount of items in this stack. */
    abstract var amount: Int

    /** The display name of this item. */
    abstract var displayName: String?

    /** The item's lore */
    abstract var lore: List<String?>

    /** The maximum size of this stack. */
    abstract val maxStackSize: Int

    /**
     * Whether this item, as an ingredient, matches any
     * item of the same type, regardless of data.
     */
    abstract val hasWildCardData: Boolean

    /**
     * Clone this item.
     *
     * @return a clone of this item
     */
    abstract fun clone(): ItemAdapter

    /**
     * Add an enchantment to the item.
     *
     * @param enchantmentId the Minecraft enchantment ID
     * @param level the enchantment level
     */
    abstract fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean = false)

    /**
     * Compare equality of this base item to another, ignoring amount.
     *
     * @param item the [ItemAdapter] to compare to
     * @return `true` if the items are similar
     */
    abstract fun isSimilar(item: ItemAdapter): Boolean

    /**
     * Checks if this item can be used as an ingredient in a recipe.
     * Ingredient can have wildcard data.
     *
     * @param ingredient the ingredient of a recipe
     * @return `true` if this item can be used as the ingredient
     */
    abstract fun matchesIngredient(ingredient: ItemAdapter): Boolean

    /**
     * Checks if the items are similar, and have the same amount.
     *
     * @return `true` if the items are equal
     */
    abstract override fun equals(other: Any?): Boolean

    /**
     * A hash code for this item.
     *
     * The hash code should remain the same between starts.
     *
     * @return the item's hash code
     */
    abstract override fun hashCode(): Int
}
