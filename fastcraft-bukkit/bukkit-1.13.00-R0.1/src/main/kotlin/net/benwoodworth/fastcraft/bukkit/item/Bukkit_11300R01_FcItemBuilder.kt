package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.*
import net.benwoodworth.fastcraft.util.`as`
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
object Bukkit_11300R01_FcItemBuilder : FcItemBuilder {

    override fun type(type: FcItemType): FcItemBuilderTyped {
        val bukkitType = type.`as`<Bukkit_11300R01_FcItemType>()
        val item = ItemStack(bukkitType.bukkitMaterial)

        return Bukkit_11300R01_FcItemBuilderTyped(item)
    }

    override fun type(type: FcItemTypes.() -> FcItemType): FcItemBuilderTyped {
        return this.type(type(Bukkit_11300R01_FcItemTypes))
    }

    override fun from(item: FcItem): FcItemBuilderTyped {
        val bukkitItem = item.`as`<Bukkit_11300R01_FcItem>()
        val copy = bukkitItem.bukkitItemStack.clone()

        return Bukkit_11300R01_FcItemBuilderTyped(copy)
    }
}
