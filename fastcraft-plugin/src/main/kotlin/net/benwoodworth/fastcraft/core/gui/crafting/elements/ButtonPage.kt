package net.benwoodworth.fastcraft.core.gui.crafting.elements

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.platform.gui.GuiLocation
import net.benwoodworth.fastcraft.platform.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.platform.gui.element.GuiLayoutChanger
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemTypeFactory
import javax.inject.Provider

@AutoFactory
class ButtonPage(
        location: GuiLocation,

        @Provided private val fastCraftLang: FastCraftLang,
        @Provided private val itemBuilder: Provider<FcItemBuilder>,
        @Provided private val itemTypeFactory: FcItemTypeFactory
) : GuiButtonAbstract(location) {

    var page by GuiLayoutChanger(0)
    var pageCount by GuiLayoutChanger(0)

    override fun getItem(location: GuiLocation) = itemBuilder.get()
            .type(itemTypeFactory.getIronSword())
            .displayName(fastCraftLang.guiToolbarPageTitle(page + 1, pageCount + 1))
            .lore(fastCraftLang.guiToolbarPageDescription(page + 1, pageCount + 1))
            .build()
}