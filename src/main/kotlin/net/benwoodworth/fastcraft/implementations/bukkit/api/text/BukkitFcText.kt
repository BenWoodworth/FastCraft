package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface BukkitFcText : FcText {


    interface Builder : FcText.Builder {

        abstract class Specific : FcText.Builder.Specific {

        }
    }

    interface Helper {

        fun getItemName(item: ItemStack): FcText

        fun getItemDisplayName(item: ItemStack): FcText?

        fun setItemDisplayName(item: ItemStack, displayName: FcText?)

        fun sendPlayerMessage(player: Player, message: FcText)
    }
}