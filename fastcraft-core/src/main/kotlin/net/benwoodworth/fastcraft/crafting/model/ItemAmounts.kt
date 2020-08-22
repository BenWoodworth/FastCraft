package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import javax.inject.Inject

class ItemAmounts private constructor(
    private val amounts: MutableMap<FcItemStack, Int>,
    private val fcItemStackFactory: FcItemStack.Factory,
    private val fcItemStackOperations: FcItemStack.Operations,
    private val fcItemOperations: FcItem.Operations,
) : FcItemStack.Operations by fcItemStackOperations,
    FcItem.Operations by fcItemOperations {

    @Inject
    constructor(
        itemStackFactory: FcItemStack.Factory,
        fcItemStackOperations: FcItemStack.Operations,
        fcItemOperations: FcItem.Operations,
    ) : this(
        amounts = mutableMapOf(),
        fcItemStackFactory = itemStackFactory,
        fcItemStackOperations = fcItemStackOperations,
        fcItemOperations = fcItemOperations,
    )

    private fun FcItemStack.asKey(): FcItemStack {
        return copy().apply { amount = type.maxAmount }
    }

    operator fun get(itemStack: FcItemStack): Int {
        return amounts.getOrDefault(itemStack.asKey(), 0)
    }

    operator fun set(itemStack: FcItemStack, amount: Int) {
        when (amount) {
            0 -> amounts.remove(itemStack.asKey())
            else -> amounts[itemStack.asKey()] = amount
        }
    }

    operator fun plusAssign(itemStack: FcItemStack) {
        if (itemStack.amount != 0) {
            val key = itemStack.asKey()
            amounts[key] = amounts.getOrDefault(key, 0) + itemStack.amount
        }
    }

    operator fun minusAssign(itemStack: FcItemStack) {
        if (itemStack.amount != 0) {
            val key = itemStack.asKey()
            amounts[key] = amounts.getOrDefault(key, 0) - itemStack.amount
        }
    }

    fun clear() {
        amounts.clear()
    }

    fun copy(): ItemAmounts {
        return ItemAmounts(
            amounts = amounts.toMutableMap(),
            fcItemStackFactory = fcItemStackFactory,
            fcItemStackOperations = fcItemStackOperations,
            fcItemOperations = fcItemOperations,
        )
    }

    fun asMap(): Map<FcItemStack, Int> {
        return amounts
    }

    fun isEmpty(): Boolean {
        return amounts.isEmpty()
    }
}
