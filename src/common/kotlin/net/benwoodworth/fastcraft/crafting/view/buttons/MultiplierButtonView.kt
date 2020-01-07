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
class MultiplierButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var multiplier: Int = 1

    var eventListener: EventListener? = null

    init {
        button.apply {
            eventListener = ButtonClickListener()

            itemType = itemTypes.anvil

            text = textFactory.createFcText("Crafting Multiplier", color = textColors.green)

            description = listOf(
                textFactory.createFcText("Left/right click to increase/decrease", color = textColors.aqua),
                textFactory.createFcText("Shift click to increment by 1", color = textColors.aqua),
                textFactory.createFcText("Middle click to reset to 1x", color = textColors.aqua)
            )

            hideItemDetails()
        }
    }

    fun update() {
        button.amount = multiplier
    }

    interface EventListener {
        fun onIncrement()
        fun onIncrementByOne()
        fun onDecrement()
        fun onDecrementByOne()
        fun onReset()
    }

    private companion object {
        val CLICK_INCREMENT = FcGuiClick.Primary()
        val CLICK_INCREMENT_ONE = FcGuiClick.Primary(FcGuiClickModifier.Shift)
        val CLICK_DECREMENT = FcGuiClick.Secondary()
        val CLICK_DECREMENT_ONE = FcGuiClick.Secondary(FcGuiClickModifier.Shift)
        val CLICK_RESET = FcGuiClick.Middle()
    }

    private inner class ButtonClickListener : FcGuiButton.EventListener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            when (click) {
                CLICK_INCREMENT -> eventListener?.onIncrement()
                CLICK_INCREMENT_ONE -> eventListener?.onIncrementByOne()
                CLICK_DECREMENT -> eventListener?.onDecrement()
                CLICK_DECREMENT_ONE -> eventListener?.onDecrementByOne()
                CLICK_RESET -> eventListener?.onReset()
            }
        }
    }
}
