package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcGuiButton {
    var listener: Listener

    fun setMaterial(material: FcMaterial)
    fun setAmount(amount: Int)
    fun setText(text: FcText)
    fun setDescription(description: List<FcText>)
    fun setProgress(progress: Double?)

    fun clear()

    fun copyItem(itemStack: FcItemStack)

    fun hideItemDetails()

    interface Listener {
        object Default : Listener

        fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {}
    }
}
