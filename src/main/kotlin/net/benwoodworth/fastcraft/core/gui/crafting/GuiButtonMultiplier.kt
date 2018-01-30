package net.benwoodworth.fastcraft.core.gui.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.api.gui.EventGuiButtonClick
import net.benwoodworth.fastcraft.api.gui.GuiButton
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory
import net.benwoodworth.fastcraft.dependencies.text.TextBuilder
import javax.inject.Provider

@AutoFactory
class GuiButtonMultiplier(
        private val guiCrafting: GuiCrafting,

        @Provided private val itemBuilder: Provider<ItemBuilder>,
        @Provided private val itemTypeFactory: ItemTypeFactory,
        @Provided private val fastCraftLang: FastCraftLang,
        @Provided private val textBuilder: Provider<TextBuilder>
) : GuiButton() {

    private val multiplierSequence = listOf(1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64)

    var multiplier = 1
        set(value) {
            field = value.coerceIn(1..64)
            updateItem()
        }

    init {
        updateItem()
    }

    private fun updateItem() {

//        val itemDisplayName = fastCraftLang //TODO
//                .guiToolbarMultiplierTitle(multiplier)
//
//        val itemLore = fastCraftLang
//                .guiToolbarMultiplierDescription(multiplier)
//                .toTypedArray()

        item = itemBuilder.get()
                .type(itemTypeFactory.getAnvil())
                .amount(multiplier)
//                .displayName(itemDisplayName)
//                .lore(*itemLore)
                .build()
    }

    private fun incrementBySequence(increase: Boolean) {
        multiplier = if (increase) {
            multiplierSequence
                    .firstOrNull { it > multiplier }
                    ?: multiplierSequence.last()
        } else {
            multiplierSequence
                    .lastOrNull { it < multiplier }
                    ?: multiplierSequence.first()
        }
    }


    override fun onClick(event: EventGuiButtonClick) {
        when {
            event.double -> Unit

            event.middle -> multiplier = 1

            event.primary && event.shift -> multiplier++
            event.primary -> incrementBySequence(true)

            event.secondary && event.shift -> multiplier--
            event.secondary -> incrementBySequence(false)

            event.number != null -> multiplier = event.number
        }

        event.player?.sendMessage(textBuilder.get()
                .text("New multiplier: ${multiplier}x")
                .build()
        )
    }
}