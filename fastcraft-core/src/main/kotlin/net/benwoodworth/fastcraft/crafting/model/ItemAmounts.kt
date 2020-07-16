package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.world.FcItemStack
import java.util.*
import javax.inject.Inject

class ItemAmounts private constructor(
    private val amounts: MutableMap<FcItemStack, Int>,
    private val itemStackFactory: FcItemStack.Factory,
    private val tcItemStack: FcItemStack.TypeClass,
) {
    private companion object {
        val keys = WeakHashMap<FcItemStack, FcItemStack>()
    }

    @Inject
    constructor(
        itemStackFactory: FcItemStack.Factory,
        tcItemStack: FcItemStack.TypeClass,
    ) : this(
        amounts = mutableMapOf(),
        itemStackFactory = itemStackFactory,
        tcItemStack = tcItemStack,
    )

    private fun FcItemStack.asKey(): FcItemStack {
        return when (tcItemStack.run { amount }) {
            1 -> this
            else -> keys.getOrPut(this) {
                itemStackFactory.copyItem(this, 1)
            }
        }
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
        tcItemStack.run {
            if (itemStack.amount != 0) {
                val key = itemStack.asKey()
                amounts[key] = amounts.getOrDefault(key, 0) + itemStack.amount
            }
        }
    }

    operator fun minusAssign(itemStack: FcItemStack) {
        tcItemStack.run {
            if (itemStack.amount != 0) {
                val key = itemStack.asKey()
                amounts[key] = amounts.getOrDefault(key, 0) - itemStack.amount
            }
        }
    }

    fun clear() {
        amounts.clear()
    }

    fun copy(): ItemAmounts {
        return ItemAmounts(amounts.toMutableMap(), itemStackFactory, tcItemStack)
    }

    fun asMap(): Map<FcItemStack, Int> {
        return amounts
    }

    fun isEmpty(): Boolean {
        return amounts.isEmpty()
    }
}
