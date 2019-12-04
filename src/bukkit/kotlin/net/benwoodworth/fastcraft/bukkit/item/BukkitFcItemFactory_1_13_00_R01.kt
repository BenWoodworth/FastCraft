package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import kotlin.reflect.KClass

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemFactory: BukkitFcItem_1_13_00_R01Factory
) : BukkitFcItemFactory {

    override fun FcItem.copy(amount: Int): FcItem {
        if (amount == this.amount) {
            return this
        }

        val itemStack = this.bukkit.toItemStack()
        itemStack.amount = amount

        return createFcItem(itemStack)
    }

    override fun createFcItem(itemStack: ItemStack): FcItem {
        return itemFactory.create(itemStack)
    }
}
