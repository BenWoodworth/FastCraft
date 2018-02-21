package net.benwoodworth.fastcraft.core.gui.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemTypeFactory
import javax.inject.Provider

@AutoFactory
class GuiButtonWorkbench(
        location: GuiLocation,

        @Provided private val itemBuilder: Provider<FcItemBuilder>,
        @Provided private val itemTypeFactory: FcItemTypeFactory,
        @Provided private val fastCraftLang: FastCraftLang
) : GuiButtonAbstract(location) {

    override fun getItem(location: GuiLocation) = itemBuilder.get()
            .type(itemTypeFactory.getAnvil())
            .displayName(fastCraftLang.guiToolbarWorkbenchTitle())
            .lore(fastCraftLang.guiToolbarWorkbenchDescription())
            .build()
}