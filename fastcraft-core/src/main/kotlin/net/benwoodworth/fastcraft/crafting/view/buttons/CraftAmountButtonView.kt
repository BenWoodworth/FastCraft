package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import java.util.*

@AutoFactory
class CraftAmountButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val sounds: FcSound.Sounds,
) {
    var craftAmount: Int? = null

    var listener: Listener = Listener.Default

    init {
        button.apply {
            listener = ButtonClickListener()

            setItemType(itemTypes.anvil)

            setText(
                textFactory.createLegacy(
                    Strings.guiToolbarAmountTitle(locale)
                )
            )

            setDescription(
                listOf(
                    textFactory.createLegacy(
                        Strings.guiToolbarAmountDescription0(locale)
                    ),
                    textFactory.createLegacy(
                        Strings.guiToolbarAmountDescription1(locale)
                    ),
                    textFactory.createLegacy(
                        Strings.guiToolbarAmountDescription2(locale)
                    )
                )
            )

            hideItemDetails()
        }
    }

    fun update() {
        button.setAmount(craftAmount ?: 1)
    }

    private companion object {
        val CLICK_INCREMENT = FcGuiClick.Primary()
        val CLICK_INCREMENT_ONE = FcGuiClick.Primary(FcGuiClickModifier.Shift)
        val CLICK_DECREMENT = FcGuiClick.Secondary()
        val CLICK_DECREMENT_ONE = FcGuiClick.Secondary(FcGuiClickModifier.Shift)
        val CLICK_RESET = FcGuiClick.Middle()
    }

    interface Listener {
        object Default : Listener

        fun onIncrement() {}
        fun onIncrementByOne() {}
        fun onDecrement() {}
        fun onDecrementByOne() {}
        fun onReset() {}
    }

    private inner class ButtonClickListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val action = when (click) {
                CLICK_INCREMENT -> listener::onIncrement
                CLICK_INCREMENT_ONE -> listener::onIncrementByOne
                CLICK_DECREMENT -> listener::onDecrement
                CLICK_DECREMENT_ONE -> listener::onDecrementByOne
                CLICK_RESET -> listener::onReset
                else -> null
            }

            action?.let {
                gui.player.playSound(sounds.uiButtonClick)
                action()
            }
        }
    }
}
