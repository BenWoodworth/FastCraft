package net.benwoodworth.fastcraft.platform.bukkit.text

import net.benwoodworth.fastcraft.platform.api.text.FcText

interface BukkitFcText : FcText {

    val text: String

    val color: BukkitFcTextColor
    val bold: Boolean?
    val italic: Boolean?
    val underlined: Boolean?
    val strikeThrough: Boolean?
    val obfuscated: Boolean?
    //val shiftClickAction: BukkitFcTextAction_Bukkit.ShiftClick?
    //val clickAction: BukkitFcTextAction_Bukkit.Click?
    //val hoverAction: BukkitFcTextAction_Bukkit.Hover?
    val extra: List<BukkitFcText>

    interface Factory : FcText.Factory

    interface Builder : FcText.Builder

    interface Converter {

        fun toLegacyText(text: BukkitFcText): String
    }
}