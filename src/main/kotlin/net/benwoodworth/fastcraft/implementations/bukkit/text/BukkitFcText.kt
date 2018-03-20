package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.dependencies.text.FcTextColor

interface BukkitFcText : FcText {

    val text: String

    var color: BukkitFcTextColor?
    var bold: Boolean?
    var italic: Boolean?
    var underlined: Boolean?
    var strikeThrough: Boolean?
    var obfuscated: Boolean?
    //var shiftClickAction: BukkitFcTextAction_Bukkit.ShiftClick?
    //var clickAction: BukkitFcTextAction_Bukkit.Click?
    //var hoverAction: BukkitFcTextAction_Bukkit.Hover?
    var extra: MutableList<BukkitFcText>

    interface Factory : FcText.Factory

    class Builder(
            private val baseText: BukkitFcText
    ) : FcText.Builder {

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

        //override fun setShiftClickAction(action: FcTextAction.ShiftClick) = also {
        //    baseText.shiftClickAction = action as BukkitFcTextAction_Bukkit.ShiftClick
        //}

        //override fun setClickAction(action: FcTextAction.Click) = also {
        //    baseText.clickAction = action as BukkitFcTextAction_Bukkit.Click
        //}

        //override fun setHoverAction(action: FcTextAction.Hover) = also {
        //    baseText.hoverAction = action as BukkitFcTextAction_Bukkit.Hover
        //}

        override fun addExtra(text: FcText) = also {
            baseText.extra.add(text as BukkitFcText)
        }
    }
}