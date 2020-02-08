package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcGuiButton {
    var listener: Listener

    fun setItemType(itemType: FcItemType)
    fun setAmount(amount: Int)
    fun setText(text: FcText)
    fun setDescription(description: List<FcText>)
    fun setProgress(progress: Double?)

    fun clear()

    fun copyItem(item: FcItem)

    fun hideItemDetails()

    interface Listener {
        object Default : Listener

        fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {}
    }
}
