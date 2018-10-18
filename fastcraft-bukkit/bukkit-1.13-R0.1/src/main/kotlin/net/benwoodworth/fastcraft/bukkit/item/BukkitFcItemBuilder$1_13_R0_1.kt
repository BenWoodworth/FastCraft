package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.api.item.*
import net.benwoodworth.fastcraft.platform.bukkit.bukkitItemStack
import net.benwoodworth.fastcraft.platform.bukkit.bukkitMaterial
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilderTyped
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
object `BukkitFcItemBuilder$1_13_R0_1` : net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemBuilder {

    override fun type(type: FcItemType): FcItemBuilderTyped {
        return `BukkitFcItemBuilderTyped$1_13_R0_1`(ItemStack(type.bukkitMaterial))
    }

    override fun type(type: FcItemTypes.() -> FcItemType): FcItemBuilderTyped {
        val material = type(`BukkitFcItemTypes$1_13_R0_1`).bukkitMaterial
        return `BukkitFcItemBuilderTyped$1_13_R0_1`(ItemStack(material))
    }

    override fun from(item: FcItem): FcItemBuilderTyped {
        return `BukkitFcItemBuilderTyped$1_13_R0_1`(item.bukkitItemStack.clone())
    }
}
