package net.benwoodworth.fastcraft.gui.crafting.elements

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiPosition
import net.benwoodworth.fastcraft.platform.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.platform.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemTypeFactory
import javax.inject.Provider

@AutoFactory
class ButtonWorkbench(
    position: FcGuiPosition,

    @Provided private val fastCraftLang: net.benwoodworth.fastcraft.lang.FastCraftLang,
    @Provided private val itemBuilder: Provider<FcItemBuilder>,
    @Provided private val itemTypeFactory: FcItemTypeFactory
) : GuiButtonAbstract(position) {

    override fun getItem(position: FcGuiPosition) = itemBuilder.get()
        .type(itemTypeFactory.getCraftingTable())
        .displayName(fastCraftLang.guiToolbarWorkbenchTitle())
        .lore(fastCraftLang.guiToolbarWorkbenchDescription())
        .build()
}