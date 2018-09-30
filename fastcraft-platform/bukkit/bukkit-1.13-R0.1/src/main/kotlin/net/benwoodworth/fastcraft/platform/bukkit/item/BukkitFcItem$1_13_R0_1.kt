package net.benwoodworth.fastcraft.platform.bukkit.item

import net.benwoodworth.fastcraft.platform.api.item.FcItemType
import net.benwoodworth.fastcraft.platform.api.text.FcText
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
inline class `BukkitFcItem$1_13_R0_1`(
    private val bukkitItem: ItemStack
) : BukkitFcItem {

    override val `bukkitItem$1_13_R0_1`: Any
        get() = bukkitItem

    override val type: `BukkitFcItemType$1_13_R0_1`
        get() = `BukkitFcItemType$1_13_R0_1`(bukkitItem.type)

    override val amount: Int
        get() = bukkitItem.amount

    override val name: FcText
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val displayName: FcText?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val lore: List<FcText?>?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val maxStackSize: Int
        get() = bukkitItem.maxStackSize

    override val durability: Int
        get() = bukkitItem.durability.toInt()
}
