package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcInventorySlot
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject

class BukkitFcInventorySlot(
    val inventory: Inventory,
    val slotIndex: Int,
    private val fcItemStackFactory: FcItemStack.Factory,
    private val fcItemStackOperations: FcItemStack.Operations,
) : FcInventorySlot {
    override var itemStack: FcItemStack?
        get() = inventory.getItem(slotIndex).fromInventoryItem()
        set(value) {
            inventory.setItem(slotIndex, value.toInventoryItem())
        }

    private fun FcItemStack?.toInventoryItem(): ItemStack {
        fcItemStackOperations.bukkit.run {
            return this@toInventoryItem
                ?.itemStack?.clone()
                ?: ItemStack(Material.AIR, 0)
        }
    }

    private fun ItemStack?.fromInventoryItem(): FcItemStack? {
        return this
            ?.takeUnless { it.type == Material.AIR }
            ?.let { fcItemStackFactory.create(it) }
    }

    override fun equals(other: Any?): Boolean {
        return other is FcInventorySlot &&
                inventory == other.inventory &&
                slotIndex == other.slotIndex
    }

    override fun hashCode(): Int {
        return Objects.hash(inventory, slotIndex)
    }

    class Factory @Inject constructor(
        private val fcItemStackFactory: FcItemStack.Factory,
        private val fcItemStackOperations: FcItemStack.Operations,
    ) {
        fun create(inventory: Inventory, slotIndex: Int): BukkitFcInventorySlot {
            return BukkitFcInventorySlot(
                inventory = inventory,
                slotIndex = slotIndex,
                fcItemStackFactory = fcItemStackFactory,
                fcItemStackOperations = fcItemStackOperations,
            )
        }
    }
}

val FcInventorySlot.inventory: Inventory
    get() = (this as BukkitFcInventorySlot).inventory

val FcInventorySlot.slotIndex: Int
    get() = (this as BukkitFcInventorySlot).slotIndex
