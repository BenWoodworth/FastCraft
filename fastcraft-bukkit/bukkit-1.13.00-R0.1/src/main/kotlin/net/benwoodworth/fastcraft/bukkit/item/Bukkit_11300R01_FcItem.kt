package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
class Bukkit_11300R01_FcItem(
    val bukkitItemStack: ItemStack
) : FcItem {

    override val type: Bukkit_11300R01_FcItemType
        get() = Bukkit_11300R01_FcItemType(bukkitItemStack.type)

    override val amount: Int
        get() = bukkitItemStack.amount

    override val name: Bukkit_11300R01_FcText
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val displayName: Bukkit_11300R01_FcText?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val lore: List<Bukkit_11300R01_FcText>?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val maxStackSize: Int
        get() = bukkitItemStack.maxStackSize

    override val durability: Int
        get() = bukkitItemStack.durability.toInt()
}
