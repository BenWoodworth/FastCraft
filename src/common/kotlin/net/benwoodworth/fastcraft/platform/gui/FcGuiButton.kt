package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcGuiButton {
    var eventListener: EventListener?

    var itemType: FcItemType

    var amount: Int

    var text: FcText

    var description: List<FcText>

    fun clear()

    fun copyItem(item: FcItem)

    fun hideItemDetails()

    interface EventListener {
        fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick)
    }
}
