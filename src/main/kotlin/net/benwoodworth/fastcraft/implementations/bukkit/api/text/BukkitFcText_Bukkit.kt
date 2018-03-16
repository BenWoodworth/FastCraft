package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BukkitFcText_Bukkit(
        var text: String
) : BukkitFcText {

    override var color: BukkitFcTextColor? = null
    override var bold: Boolean? = null
    override var italic: Boolean? = null
    override var underlined: Boolean? = null
    override var strikeThrough: Boolean? = null
    override var obfuscated: Boolean? = null
    override var shiftClickAction: BukkitFcTextAction.ShiftClick? = null
    override var clickAction: BukkitFcTextAction.Click? = null
    override var hoverAction: BukkitFcTextAction.Hover? = null
    override var extra: MutableList<BukkitFcText> = mutableListOf()

    class Builder : BukkitFcText.Builder {
        override fun setText(text: String): FcText.Builder.Specific {
            return BukkitFcText.Builder.Specific(BukkitFcText_Bukkit(text))
        }
    }

    class Helper : BukkitFcText.Helper {
        override fun getItemName(item: ItemStack): BukkitFcText {
            TODO()
        }

        override fun getItemDisplayName(item: ItemStack): BukkitFcText? {
            return if (item.hasItemMeta()) {
                item.itemMeta.displayName?.let { BukkitFcText_Bukkit(it) }
            } else {
                null
            }
        }

        override fun setItemDisplayName(item: ItemStack, displayName: FcText?) {
            item.itemMeta.displayName = (displayName as BukkitFcText_Bukkit?)?.text
        }

        override fun sendPlayerMessage(player: Player, message: FcText) {
            player.sendMessage((message as BukkitFcText_Bukkit).text)
        }
    }
}