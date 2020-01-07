package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import java.util.*
import javax.inject.Inject

class ItemAmounts private constructor(
    private val ingredients: MutableMap<FcItem, Int>,
    private val itemFactory: FcItemFactory
) {
    private companion object {
        val keys = WeakHashMap<FcItem, FcItem>()
    }

    @Inject
    constructor(itemFactory: FcItemFactory) : this(mutableMapOf(), itemFactory)

    private fun FcItem.asKey(): FcItem {
        return when (amount) {
            1 -> this
            else -> keys.getOrPut(this) {
                itemFactory.copyItem(this, 1)
            }
        }
    }

    operator fun get(item: FcItem): Int {
        return ingredients.getOrDefault(item, 0)
    }

    operator fun set(item: FcItem, amount: Int) {
        when (amount) {
            0 -> ingredients.remove(item.asKey())
            else -> ingredients[item.asKey()] = amount
        }
    }

    fun copy(): ItemAmounts {
        return ItemAmounts(ingredients.toMutableMap(), itemFactory)
    }
}
