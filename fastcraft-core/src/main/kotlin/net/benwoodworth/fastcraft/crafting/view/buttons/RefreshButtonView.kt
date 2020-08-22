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

class RefreshButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    private val fcTextFactory: FcText.Factory,
    private val fcSoundFactory: FcSound.Factory,
    private val config: FastCraftConfig,
    private val fcPlayerOperations: FcPlayer.Operations,
) {
    var enabled: Boolean = true

    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()

        update()
    }

    fun update() {
        val c = config.layout.buttons.refresh

        button.apply {
            clear()

            if (enabled) {
                copyItem(c.item)

                setText(
                    fcTextFactory.createLegacy(
                        Strings.guiButtonRefreshTitle(locale)
                    )
                )

                setDescription(
                    listOf(
                        fcTextFactory.createLegacy(
                            Strings.guiButtonRefreshDescription0(locale)
                        )
                    )
                )

                hideItemDetails()
            }
        }
    }

    private companion object {
        val CLICK_REFRESH = FcGuiClick.Primary()
    }

    interface Listener {
        object Default : Listener

        fun onRefresh() {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            if (enabled) {
                val action = when (click) {
                    CLICK_REFRESH -> listener::onRefresh
                    else -> null
                }

                action?.let {
                    fcPlayerOperations.run { gui.player.playSound(fcSoundFactory.uiButtonClick, Config.buttonVolume) }
                    action()
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val fcTextFactory: FcText.Factory,
        private val fcSoundsFactory: FcSound.Factory,
        private val fastCraftConfig: FastCraftConfig,
        private val fcPlayerOperations: FcPlayer.Operations,
    ) {
        fun create(button: FcGuiButton, locale: Locale): RefreshButtonView {
            return RefreshButtonView(
                button = button,
                locale = locale,
                fcTextFactory = fcTextFactory,
                fcSoundFactory = fcSoundsFactory,
                config = fastCraftConfig,
                fcPlayerOperations = fcPlayerOperations,
            )
        }
    }
}
