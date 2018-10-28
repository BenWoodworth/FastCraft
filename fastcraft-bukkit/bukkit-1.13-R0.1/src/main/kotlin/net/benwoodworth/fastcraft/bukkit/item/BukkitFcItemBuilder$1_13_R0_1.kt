package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.*
import net.benwoodworth.fastcraft.util.`as`
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
object `BukkitFcItemBuilder$1_13_R0_1` : FcItemBuilder {

    override fun type(type: FcItemType): FcItemBuilderTyped {
        val bukkitType = type.`as`<`BukkitFcItemType$1_13_R0_1`>()
        val item = ItemStack(bukkitType.bukkitMaterial)

        return `BukkitFcItemBuilderTyped$1_13_R0_1`(item)
    }

    override fun type(type: FcItemTypes.() -> FcItemType): FcItemBuilderTyped {
        return this.type(type(`BukkitFcItemTypes$1_13_R0_1`))
    }

    override fun from(item: FcItem): FcItemBuilderTyped {
        val bukkitItem = item.`as`<`BukkitFcItem$1_13_R0_1`>()
        val copy = bukkitItem.bukkitItemStack.clone()

        return `BukkitFcItemBuilderTyped$1_13_R0_1`(copy)
    }
}
