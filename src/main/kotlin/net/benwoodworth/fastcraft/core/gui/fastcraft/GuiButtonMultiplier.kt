package net.benwoodworth.fastcraft.core.gui.fastcraft

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.gui.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory
import net.benwoodworth.fastcraft.dependencies.text.TextBuilder
import javax.inject.Provider

@AutoFactory
class GuiButtonMultiplier(
        private val guiFastCraft: GuiFastCraft,

        @Provided itemBuilder: ItemBuilder,
        @Provided itemTypeFactory: ItemTypeFactory,
        @Provided private val textBuilder: Provider<TextBuilder>
) : GuiButton(itemBuilder
        .type(itemTypeFactory.getAnvil())
        .amount(1)
        .displayName(textBuilder.get()
                .text("Multiplier") // TODO Localize
                .build()
        )
        .build()
) {

    override fun onClick(event: EventGuiButtonClick) {
        event.player?.sendMessage(textBuilder.get()
                .text("You clicked the multiplier button!!")
                .build()
        )
    }
}