package net.benwoodworth.fastcraft.core.gui.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory
import javax.inject.Provider

@AutoFactory
class GuiButtonMultiplier(
        x: Int,
        y: Int,

        @Provided private val itemBuilder: Provider<ItemBuilder>,
        @Provided private val itemTypeFactory: ItemTypeFactory,
        @Provided private val fastCraftLang: FastCraftLang
) : GuiButtonAbstract(x, y) {

    var multiplier: Int = 1
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))
        }

    override fun getItem(x: Int, y: Int) = itemBuilder.get()
            .type(itemTypeFactory.getAnvil())
            .amount(multiplier)
            .displayName(fastCraftLang.guiToolbarMultiplierTitle(multiplier))
            .lore(fastCraftLang.guiToolbarMultiplierDescription(multiplier))
            .build()
}