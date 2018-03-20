package net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcTextColor
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcText_Bukkit_1_7_2_R0_3(
        override val text: String
) : BukkitFcText_Bukkit {

    override var color: BukkitFcTextColor? = null
    override var bold: Boolean? = null
    override var italic: Boolean? = null
    override var underlined: Boolean? = null
    override var strikeThrough: Boolean? = null
    override var obfuscated: Boolean? = null
    //override var shiftClickAction: BukkitFcTextAction_Bukkit.ShiftClick? = null
    //override var clickAction: BukkitFcTextAction_Bukkit.Click? = null
    //override var hoverAction: BukkitFcTextAction_Bukkit.Hover? = null
    override var extra: MutableList<BukkitFcText> = mutableListOf()

    class Factory @Inject constructor(
    ) : BukkitFcText_Bukkit.Factory {

        override fun buildText(text: String): FcText.Builder {
            return BukkitFcText.Builder(
                    BukkitFcText_Bukkit_1_7_2_R0_3(text)
            )
        }
    }
}