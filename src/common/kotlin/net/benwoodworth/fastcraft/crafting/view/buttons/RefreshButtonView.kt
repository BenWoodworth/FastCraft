package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class RefreshButtonView(
    val button: FcGuiButton,
    @Provided val itemTypes: FcItemTypes,
    @Provided val textFactory: FcTextFactory,
    @Provided val textColors: FcTextColors
) {
    init {
        button.apply {
            itemType = itemTypes.netherStar

            text = textFactory.createFcText("Refresh", color = textColors.green)

            description = listOf(
                textFactory.createFcText("Refresh the FastCraft interface", color = textColors.aqua)
            )

            hideItemDetails()
        }
    }
}
