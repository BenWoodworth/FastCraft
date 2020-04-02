package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.Strings
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import java.util.*

@AutoFactory
class RefreshButtonView(
    private val button: FcGuiButton,
    private val locale: Locale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors,
) {
    var enabled: Boolean = true

    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()

        update()
    }

    fun update() {
        button.apply {
            clear()

            if (enabled) {
                setItemType(itemTypes.netherStar)

                setText(
                    textFactory.createLegacy(
                        Strings.guiToolbarRefreshTitle(locale)
                    )
                )

                setDescription(
                    listOf(
                        textFactory.createLegacy(
                            Strings.guiToolbarRefreshDescription0(locale)
                        )
                    )
                )

                hideItemDetails()
            }
        }
    }

    interface Listener {
        object Default : Listener

        fun onRefresh() {}
    }

    private companion object {
        val CLICK_REFRESH = FcGuiClick.Primary()
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            if (enabled) {
                when (click) {
                    CLICK_REFRESH -> listener.onRefresh()
                }
            }
        }
    }
}
