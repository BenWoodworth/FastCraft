package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*
import javax.inject.Inject

class WorkbenchButtonView(
    button: FcGuiButton,
    private val locale: Locale,
    private val textFactory: FcText.Factory,
    private val sounds: FcSound.Factory,
    config: FastCraftConfig,
) {
    var listener: Listener = Listener.Default

    init {
        val c = config.fastCraftUi.buttons.craftingGrid

        button.apply {
            listener = ButtonListener()

            setItem(c.item)

            setText(
                textFactory.createLegacy(
                    Strings.guiToolbarWorkbenchTitle(locale)
                )
            )

            setDescription(
                listOf(
                    textFactory.createLegacy(
                        Strings.guiToolbarWorkbenchDescription0(locale)
                    ),
                    textFactory.createLegacy(
                        Strings.guiToolbarWorkbenchDescription1(locale)
                    )
                )
            )

            hideItemDetails()
        }
    }

    private companion object {
        val CLICK_OPEN_WORKBENCH = FcGuiClick.Primary()
    }

    interface Listener {
        object Default : Listener

        fun onOpenWorkbench() {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val action = when (click) {
                CLICK_OPEN_WORKBENCH -> listener::onOpenWorkbench
                else -> null
            }

            action?.let {
                gui.player.playSound(sounds.uiButtonClick, Config.buttonVolume)
                action()
            }
        }
    }

    class Factory @Inject constructor(
        private val textFactory: FcText.Factory,
        private val sounds: FcSound.Factory,
        private val config: FastCraftConfig,
    ) {
        fun create(button: FcGuiButton, locale: Locale): WorkbenchButtonView {
            return WorkbenchButtonView(
                button = button,
                locale = locale,
                textFactory = textFactory,
                sounds = sounds,
                config = config,
            )
        }
    }
}
