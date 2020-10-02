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
    private val playerCommand: String?,
    private val serverCommand: String?,
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

        fun onClick(playerCommand: String?, serverCommand: String?) {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            listener.onClick(playerCommand, serverCommand)

            if (playerCommand != null || serverCommand != null) {
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
            playerCommand: String?,
            serverCommand: String?,
        ): CustomButtonView {
            return CustomButtonView(
                button = button,
                playerCommand = playerCommand,
                serverCommand = serverCommand,
                fcSoundFactory = fcSoundFactory,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
