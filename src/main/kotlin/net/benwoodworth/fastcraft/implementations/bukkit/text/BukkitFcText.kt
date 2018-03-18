package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.FcText
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface BukkitFcText : FcText {

    interface Factory : FcText.Factory {

        fun getItemName(item: ItemStack): BukkitFcText

        fun getItemDisplayName(item: ItemStack): BukkitFcText?
        fun setItemDisplayName(item: ItemStack, displayName: FcText?)

        fun getItemLore(item: ItemStack): List<BukkitFcText?>?
        fun setItemLore(item: ItemStack, lore: List<BukkitFcText?>?)

        fun sendPlayerMessage(player: Player, message: FcText)
    }

    interface Builder : FcText.Builder
}