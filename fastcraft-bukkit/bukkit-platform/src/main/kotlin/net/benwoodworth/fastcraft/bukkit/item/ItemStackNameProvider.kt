package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack

interface ItemStackNameProvider {
    fun getName(itemStack: ItemStack): FcText
}
