package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class RefreshButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var enabled: Boolean = false

    var eventListener: EventListener? = null

    private companion object {
        val CLICK_REFRESH = FcGuiClick.Primary()
    }

    init {
        button.onClick = { event ->
            if (enabled) {
                when (event.click) {
                    CLICK_REFRESH -> eventListener?.onRefresh()
                }
            }
        }

        update()
    }

    fun update() {
        button.apply {
            clear()

            if (enabled) {
                itemType = itemTypes.netherStar

                text = textFactory.createFcText("Refresh", color = textColors.green)

                description = listOf(
                    textFactory.createFcText("Refresh the FastCraft interface", color = textColors.aqua)
                )

                hideItemDetails()
            }
        }
    }

    interface EventListener {
        fun onRefresh()
    }
}
