package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class WorkbenchButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var listener: Listener = Listener.Default

    init {
        button.apply {
            listener = ButtonListener()

            itemType = itemTypes.craftingTable

            text = textFactory.createFcText("Crafting Grid", color = textColors.green)

            description = listOf(
                textFactory.createFcText("Open a 3x3 crafting grid", color = textColors.aqua),
                textFactory.createFcText(
                    color = textColors.green,
                    extra = listOf(
                        textFactory.createFcText("Use "),
                        textFactory.createFcText("/fc toggle", color = textColors.aqua, italic = true),
                        textFactory.createFcText(" to disable FastCraft")
                    )
                )
            )

            hideItemDetails()
        }
    }

    interface Listener {
        object Default : Listener

        fun onOpenWorkbench() {}
    }

    private companion object {
        val CLICK_OPEN_WORKBENCH = FcGuiClick.Primary()
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            when (click) {
                CLICK_OPEN_WORKBENCH -> listener.onOpenWorkbench()
            }
        }
    }
}
