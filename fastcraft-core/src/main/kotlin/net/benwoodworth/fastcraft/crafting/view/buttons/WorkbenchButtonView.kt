package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*
import javax.inject.Inject

class WorkbenchButtonView(
    button: FcGuiButton,
    private val locale: Locale,
    private val fcTextFactory: FcText.Factory,
    private val fcSounds: FcSound.Factory,
    config: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
) : FcPlayer.Operations by fcPlayerOperations {
    var listener: Listener = Listener.Default

    init {
        val c = config.layout.buttons.craftingGrid

        button.apply {
            listener = ButtonListener()

            copyItem(c.item)

            setText(
                fcTextFactory.createLegacy(
                    Strings.guiButtonCraftingGridTitle(locale)
                )
            )

            setDescription(
                listOf(
                    fcTextFactory.createLegacy(
                        Strings.guiButtonCraftingGridDescription0(locale)
                    ),
                    fcTextFactory.createLegacy(
                        Strings.guiButtonCraftingGridDescription1(locale)
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
        fun create(button: FcGuiButton, locale: Locale): WorkbenchButtonView {
            return WorkbenchButtonView(
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
