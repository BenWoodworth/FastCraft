package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface BukkitFcText : FcText {

    val helper: Helper

    var color: BukkitFcTextColor?
    var bold: Boolean?
    var italic: Boolean?
    var underlined: Boolean?
    var strikeThrough: Boolean?
    var obfuscated: Boolean?
    var shiftClickAction: BukkitFcTextAction.ShiftClick?
    var clickAction: BukkitFcTextAction.Click?
    var hoverAction: BukkitFcTextAction.Hover?
    var extra: MutableList<BukkitFcText>

    interface Legacy : BukkitFcText {
        val text: String
    }

    interface Builder : FcText.Builder {

        class Specific(
                private val baseText: BukkitFcText
        ) : FcText.Builder.Specific {

            override fun build() = baseText

            override fun setColor(color: FcTextColor) = also {
                baseText.color = color as BukkitFcTextColor
            }

            override fun setBold(bold: Boolean) = also {
                baseText.bold = bold
            }

            override fun setItalic(italic: Boolean) = also {
                baseText.italic = italic
            }

            override fun setUnderlined(underlined: Boolean) = also {
                baseText.underlined = underlined
            }

            override fun setStrikeThrough(strikeThrough: Boolean) = also {
                baseText.strikeThrough = strikeThrough
            }

            override fun setObfuscated(obfuscated: Boolean) = also {
                baseText.obfuscated = obfuscated
            }

            override fun setShiftClickAction(action: FcTextAction.ShiftClick) = also {
                baseText.shiftClickAction = action as BukkitFcTextAction.ShiftClick
            }

            override fun setClickAction(action: FcTextAction.Click) = also {
                baseText.clickAction = action as BukkitFcTextAction.Click
            }

            override fun setHoverAction(action: FcTextAction.Hover) = also {
                baseText.hoverAction = action as BukkitFcTextAction.Hover
            }

            override fun addExtra(text: FcText) = also {
                baseText.extra.add(text as BukkitFcText)
            }
        }
    }

    interface Helper {

        fun getItemName(item: ItemStack): BukkitFcText

        fun getItemDisplayName(item: ItemStack): BukkitFcText?

        fun setItemDisplayName(item: ItemStack, displayName: FcText?)

        fun getItemLore(item: ItemStack): List<BukkitFcText?>?

        fun setItemLore(item: ItemStack, lore: List<BukkitFcText?>?)

        fun sendPlayerMessage(player: Player, message: FcText)
    }
}