package net.benwoodworth.fastcraft.core.dependencies.inventory

/**
 * A Minecraft item.
 *
 * @param TBase the item type
 */
interface FcItem<TBase> {

    /** The base item. */
    val baseItem: TBase

    /** The amount of items in this stack. */
    var amount: Int

    /** The display name of this item. */
    var displayName: String?

    /** The item's lore */
    var lore: List<String?>

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
    fun clone(): FcItem<TBase>

    /**
     * Add an enchantment to the item.
     *
     * @param enchantmentId the Minecraft enchantment ID
     * @param level         the enchantment level
     */
    fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean = false)

    /**
     * Compare equality of this base item to another, ignoring amount.
     *
     * @param item the [FcItem] to compare to
     * @return `true` if the items are similar
     */
    fun isSimilar(item: FcItem<TBase>): Boolean

    /**
     * Checks if this item can be used as an ingredient in a recipe.
     * Ingredient can have wildcard data.
     *
     * @param ingredient the ingredient of a recipe
     * @return `true` if this item can be used as the ingredient
     */
    fun matchesIngredient(ingredient: FcItem<TBase>): Boolean

    /**
     * Checks if the items are similar, and have the same amount.
     *
     * @return `true` if the items are equal
     */
    override fun equals(other: Any?): Boolean

    /**
     * A hash code for this item.
     *
     * The hash code should remain the same between starts.
     *
     * @return the item's hash code
     */
    override fun hashCode(): Int
}
