package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class PageButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var page: Int = 1
    var pageCount: Int = 1

    var eventListener: EventListener? = null

    init {
        button.apply {
            eventListener = ButtonEventListener()

            itemType = itemTypes.ironSword

            description = listOf(
                textFactory.createFcText("Left click: next page", color = textColors.aqua),
                textFactory.createFcText("Right click: previous page", color = textColors.aqua),
                textFactory.createFcText("Shift click: first page", color = textColors.aqua)
            )

            hideItemDetails()
        }

        update()
    }

    fun update() {
        button.apply {
            text = textFactory.createFcText(
                text = "Page ${page}/${pageCount}",
                color = textColors.green
            )
        }
    }

    interface EventListener {
        fun onPageNext()
        fun onPagePrevious()
        fun onPageFirst()
    }

    private companion object {
        val CLICK_PAGE_NEXT = FcGuiClick.Primary()
        val CLICK_PAGE_PREVIOUS = FcGuiClick.Secondary()
        val CLICK_PAGE_FIRST = FcGuiClick.Primary(FcGuiClickModifier.Shift)
    }

    private inner class ButtonEventListener : FcGuiButton.EventListener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            when (click) {
                CLICK_PAGE_NEXT -> eventListener?.onPageNext()
                CLICK_PAGE_PREVIOUS -> eventListener?.onPagePrevious()
                CLICK_PAGE_FIRST -> eventListener?.onPageFirst()
            }
        }
    }
}
