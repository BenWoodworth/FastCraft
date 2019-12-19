package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import kotlin.properties.Delegates

@AutoFactory
class MultiplierButtonView(
    val button: FcGuiButton,
    @Provided val itemTypes: FcItemTypes,
    @Provided val textFactory: FcTextFactory,
    @Provided val textColors: FcTextColors
) {
    var multiplier: Int by Delegates.observable(1) { _, _, new ->
        button.amount = new
    }

    init {
        button.apply {
            itemType = itemTypes.anvil

            text = textFactory.createFcText("Crafting Multiplier", color = textColors.green)

            description = listOf(
                textFactory.createFcText("Left/right click to increase/decrease", color = textColors.aqua),
                textFactory.createFcText("Shift click to increment by 1", color = textColors.aqua),
                textFactory.createFcText("Middle click to reset to 1x", color = textColors.aqua)
            )

            hideItemDetails()
        }
    }
}
