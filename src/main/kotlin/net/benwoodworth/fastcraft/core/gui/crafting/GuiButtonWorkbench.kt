package net.benwoodworth.fastcraft.core.gui.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.api.gui.EventGuiButtonClick
import net.benwoodworth.fastcraft.api.gui.GuiButton
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory

@AutoFactory
class GuiButtonWorkbench(
        @Provided private val itemBuilder: ItemBuilder,
        @Provided private val itemTypeFactory: ItemTypeFactory,
        @Provided private val fastCraftLang: FastCraftLang
) : GuiButton(
        itemBuilder
                .type(itemTypeFactory.getCraftingTable())
                //.displayName(fastCraftLang.guiToolbarWorkbenchTitle()) //TODO
                //.lore(*fastCraftLang.guiToolbarWorkbenchDescription().toTypedArray())
                .build()
) {

    override fun onClick(event: EventGuiButtonClick) {
        TODO("Open workbench")
    }
}