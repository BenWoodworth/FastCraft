package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcSound
import javax.inject.Inject

class CustomButtonView(
    private val button: FcGuiButton,
    private val command: String?,
    private val fcSoundFactory: FcSound.Factory,
    fcPlayerOperations: FcPlayer.Operations,
) : FcPlayer.Operations by fcPlayerOperations {
    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()
    }

    fun update() {
//        button.copyItem(customButton.item)
    }

    interface Listener {
        object Default : Listener

        fun onClick(command: String?) {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            listener.onClick(command)

            if (command != null) {
                gui.player.playSound(fcSoundFactory.uiButtonClick, Config.buttonVolume)
            }
        }
    }

    class Factory @Inject constructor(
        private val fcSoundFactory: FcSound.Factory,
        private val fcPlayerOperations: FcPlayer.Operations,
    ) {
        fun create(
            button: FcGuiButton,
            command: String?,
        ): CustomButtonView {
            return CustomButtonView(
                button = button,
                command = command,
                fcSoundFactory = fcSoundFactory,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
