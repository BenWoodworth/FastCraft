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

class CustomButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    private val playerCommand: String?,
    private val serverCommand: String?,
    private val fcTextFactory: FcText.Factory,
    private val fcSoundFactory: FcSound.Factory,
    config: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
) : FcPlayer.Operations by fcPlayerOperations {
    var listener: Listener = Listener.Default

    init {
        val c = config.layout.buttons.page

        button.apply {
            listener = ButtonListener()

            copyItem(c.item)

            setDescription(
                listOf(
                    fcTextFactory.createLegacy(
                        Strings.guiButtonPageDescription0(locale)
                    ),
                    fcTextFactory.createLegacy(
                        Strings.guiButtonPageDescription1(locale)
                    ),
                    fcTextFactory.createLegacy(
                        Strings.guiButtonPageDescription2(locale)
                    )
                )
            )

            hideItemDetails()
        }

        update()
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
        private val fcTextFactory: FcText.Factory,
        private val fcSoundFactory: FcSound.Factory,
        private val fastCraftConfig: FastCraftConfig,
        private val fcPlayerOperations: FcPlayer.Operations,
    ) {
        fun create(
            button: FcGuiButton,
            locale: Locale,
            playerCommand: String?,
            serverCommand: String?,
        ): CustomButtonView {
            return CustomButtonView(
                button = button,
                locale = locale,
                playerCommand = playerCommand,
                serverCommand = serverCommand,
                fcTextFactory = fcTextFactory,
                fcSoundFactory = fcSoundFactory,
                config = fastCraftConfig,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
