package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcInventorySlotFactory_1_13_00_R01 @Inject constructor(
    private val itemFactory: FcItemFactory
) : BukkitFcInventorySlotFactory {
    override fun createSlot(getItem: () -> ItemStack?, setItem: (item: ItemStack) -> Unit): FcInventorySlot {
        return object : BukkitFcInventorySlot {
            override var item: FcItem?
                get() = getItem().fromInventoryItem()
                set(value) = setItem(value.toInventoryItem())
        }
    }

    override fun createSlot(inventory: Inventory, slotIndex: Int): FcInventorySlot {
        return createSlot(
            getItem = { inventory.getItem(slotIndex) },
            setItem = { inventory.setItem(slotIndex, it) }
        )
    }

    private fun FcItem?.toInventoryItem(): ItemStack {
        return this
            ?.toItemStack()
            ?: ItemStack(Material.AIR, 0)
    }

    private fun ItemStack?.fromInventoryItem(): FcItem? {
        return this
            ?.takeUnless { it.type == Material.AIR }
            ?.let { itemFactory.createFcItem(it) }
    }
}
