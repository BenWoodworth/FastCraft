package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class WorkbenchButtonView(
    val button: FcGuiButton,
    @Provided val itemTypes: FcItemTypes,
    @Provided val textFactory: FcTextFactory,
    @Provided val textColors: FcTextColors
) {
    init {
        button.apply {
            itemType = itemTypes.craftingTable

            text = textFactory.createFcText("Crafting Grid", color = textColors.green)

            description = listOf(
                textFactory.createFcText("Open a 3x3 crafting grid", color = textColors.aqua),
                textFactory.createFcText(
                    color = textColors.green,
                    extra = listOf(
                        textFactory.createFcText("Use "),
                        textFactory.createFcText("/fc toggle", color = textColors.aqua, italic = true),
                        textFactory.createFcText(" to disable FastCraft")
                    )
                )
            )

            hideItemDetails()
        }
    }
}
