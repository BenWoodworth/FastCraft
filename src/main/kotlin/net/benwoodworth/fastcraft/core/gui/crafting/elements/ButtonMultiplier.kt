package net.benwoodworth.fastcraft.core.gui.crafting.elements

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiLayoutChanger
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemTypeFactory
import javax.inject.Provider

@AutoFactory
class ButtonMultiplier(
        location: GuiLocation,

        @Provided private val fastCraftLang: FastCraftLang,
        @Provided private val itemBuilder: Provider<FcItemBuilder>,
        @Provided private val itemTypeFactory: FcItemTypeFactory
) : GuiButtonAbstract(location) {

    var multiplier by GuiLayoutChanger(1)

    override fun getItem(location: GuiLocation) = itemBuilder.get()
            .type(itemTypeFactory.getAnvil())
            .amount(multiplier)
            .displayName(fastCraftLang.guiToolbarMultiplierTitle(multiplier))
            .lore(fastCraftLang.guiToolbarMultiplierDescription(multiplier))
            .build()
}