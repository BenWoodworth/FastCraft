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
import net.benwoodworth.fastcraft.platform.world.FcItem
import java.util.*
import javax.inject.Inject

class RefreshButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    private val textFactory: FcText.Factory,
    private val sounds: FcSound.Factory,
    private val config: FastCraftConfig,
    private val fcPlayerTypeClass: FcPlayer.TypeClass,
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
                    textFactory.createLegacy(
                        Strings.guiButtonRefreshTitle(locale)
                    )
                )

                setDescription(
                    listOf(
                        textFactory.createLegacy(
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
                    fcPlayerTypeClass.run { gui.player.playSound(sounds.uiButtonClick, Config.buttonVolume) }
                    action()
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val items: FcItem.Factory,
        private val textFactory: FcText.Factory,
        private val sounds: FcSound.Factory,
        private val config: FastCraftConfig,
        private val fcPlayerTypeClass: FcPlayer.TypeClass,
    ) {
        fun create(button: FcGuiButton, locale: Locale): RefreshButtonView {
            return RefreshButtonView(
                button = button,
                locale = locale,
                textFactory = textFactory,
                sounds = sounds,
                config = config,
                fcPlayerTypeClass = fcPlayerTypeClass,
            )
        }
    }
}
