package net.benwoodworth.fastcraft.crafting.view.buttons

import net.benwoodworth.fastcraft.Config
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import java.util.*
import javax.inject.Inject

class PageButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    private val itemTypes: FcItemType.Factory,
    private val textFactory: FcTextFactory,
    private val sounds: FcSound.Factory,
) {
    var page: Int = 1
    var pageCount: Int = 1

    var listener: Listener = Listener.Default

    init {
        button.apply {
            listener = ButtonListener()

            setItemType(itemTypes.ironSword)

            setDescription(
                listOf(
                    textFactory.createLegacy(
                        Strings.guiToolbarPageDescription0(locale)
                    ),
                    textFactory.createLegacy(
                        Strings.guiToolbarPageDescription1(locale)
                    ),
                    textFactory.createLegacy(
                        Strings.guiToolbarPageDescription2(locale)
                    )
                )
            )

            hideItemDetails()
        }

        update()
    }

    fun update() {
        button.apply {
            setText(
                textFactory.createLegacy(
                    Strings.guiToolbarPageTitle(locale, page, pageCount)
                )
            )

            setProgress(page.toDouble() / pageCount)
        }
    }

    private companion object {
        val CLICK_PAGE_NEXT = FcGuiClick.Primary()
        val CLICK_PAGE_PREVIOUS = FcGuiClick.Secondary()
        val CLICK_PAGE_FIRST = FcGuiClick.Primary(FcGuiClickModifier.Shift)
    }

    interface Listener {
        object Default : Listener

        fun onPageNext() {}
        fun onPagePrevious() {}
        fun onPageFirst() {}
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val action = when (click) {
                CLICK_PAGE_NEXT -> listener::onPageNext
                CLICK_PAGE_PREVIOUS -> listener::onPagePrevious
                CLICK_PAGE_FIRST -> listener::onPageFirst
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
        fun create(button: FcGuiButton, locale: Locale): PageButtonView {
            return PageButtonView(
                button = button,
                locale = locale,
                itemTypes = itemTypes,
                textFactory = textFactory,
                sounds = sounds,
            )
        }
    }
}
