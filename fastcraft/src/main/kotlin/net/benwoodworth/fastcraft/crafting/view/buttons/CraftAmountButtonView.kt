package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*
import javax.inject.Inject

class CraftAmountButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    private val fcTextFactory: FcText.Factory,
    private val fcSounds: FcSound.Factory,
    config: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
) : FcPlayer.Operations by fcPlayerOperations {
    var craftAmount: Int? = null

    var listener: Listener = Listener.Default

    init {
        val c = config.layout.buttons.craftAmount

        button.apply {
            listener = ButtonClickListener()

            copyItem(c.item)

            setText(
                fcTextFactory.createLegacy(
                    Strings.guiButtonCraftAmountTitle(locale)
                )
            )

            setDescription(
                listOf(
                    fcTextFactory.createLegacy(
                        Strings.guiButtonCraftAmountDescription0(locale)
                    ),
                    fcTextFactory.createLegacy(
                        Strings.guiButtonCraftAmountDescription1(locale)
                    ),
                    fcTextFactory.createLegacy(
                        Strings.guiButtonCraftAmountDescription2(locale)
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
                gui.player.playSound(fcSounds.uiButtonClick, Config.buttonVolume)
                action()
            }
        }
    }

    class Factory @Inject constructor(
        private val fcTextFactory: FcText.Factory,
        private val fcSoundFactory: FcSound.Factory,
        private val fastCraftConfig: FastCraftConfig,
        private val fcPlayerOperations: FcPlayer.Operations,
    ) {
        fun create(button: FcGuiButton, locale: Locale): CraftAmountButtonView {
            return CraftAmountButtonView(
                button = button,
                locale = locale,
                fcTextFactory = fcTextFactory,
                fcSounds = fcSoundFactory,
                config = fastCraftConfig,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
