package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcItemStack_1_13(
    itemStack: ItemStack,
    itemTypes: FcItemType.Factory,
    textFactory: FcText.Factory,
) : BukkitFcItemStack_1_7(
    bukkitItemStack = itemStack,
    itemTypes = itemTypes,
    textFactory = textFactory
) {
    override val type: FcItemType
        get() = itemTypes.fromMaterial(bukkitItemStack.type)

    @Singleton
    open class Factory @Inject constructor(
        itemTypes: FcItemType.Factory,
        textFactory: FcText.Factory,
    ) : BukkitFcItemStack_1_7.Factory(
        itemTypes = itemTypes,
        textFactory = textFactory
    ) {
        override fun create(itemStack: ItemStack): FcItemStack {
            return BukkitFcItemStack_1_13(
                itemStack = itemStack,
                itemTypes = itemTypes,
                textFactory = textFactory
            )
        }
    }
}
