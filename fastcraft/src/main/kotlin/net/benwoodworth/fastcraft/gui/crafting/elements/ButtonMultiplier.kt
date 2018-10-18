package net.benwoodworth.fastcraft.gui.crafting.elements

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiLayoutChanger
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import javax.inject.Provider

@AutoFactory
class ButtonMultiplier(
    position: FcGuiPosition,

    @Provided private val fastCraftLang: net.benwoodworth.fastcraft.lang.FastCraftLang,
    @Provided private val itemBuilder: Provider<FcItemBuilder>,
    @Provided private val itemTypes: FcItemTypes
) : GuiButtonAbstract(position) {

    var multiplier by GuiLayoutChanger(1)

    override fun getItem(position: FcGuiPosition) = itemBuilder.get()
        .type(itemTypes.getAnvil())
        .amount(multiplier)
        .displayName(fastCraftLang.guiToolbarMultiplierTitle(multiplier))
        .lore(fastCraftLang.guiToolbarMultiplierDescription(multiplier))
        .build()
}
