package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import java.util.*
import javax.inject.Inject

class WorkbenchButtonView(
    button: FcGuiButton,
    private val locale: Locale,
    private val itemTypes: FcItemType.Factory,
    private val textFactory: FcTextFactory,
    private val sounds: FcSound.Factory,
) {
    var listener: Listener = Listener.Default

    init {
        button.apply {
            listener = ButtonListener()

            setItemType(itemTypes.craftingTable)

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
        private val itemTypes: FcItemType.Factory,
        private val textFactory: FcTextFactory,
        private val sounds: FcSound.Factory,
    ) {
        fun create(button: FcGuiButton, locale: Locale): WorkbenchButtonView {
            return WorkbenchButtonView(
                button = button,
                locale = locale,
                itemTypes = itemTypes,
                textFactory = textFactory,
                sounds = sounds,
            )
        }
    }
}
