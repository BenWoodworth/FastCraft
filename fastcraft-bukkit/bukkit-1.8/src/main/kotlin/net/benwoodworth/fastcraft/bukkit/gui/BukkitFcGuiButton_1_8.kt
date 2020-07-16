package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcGuiButton_1_8(
    inventory: Inventory,
    slotIndex: Int,
    locale: Locale,
    textFactory: FcText.Factory,
    textConverter: FcTextConverter,
    tcItem: FcItem.TypeClass,
    tcItemStack: FcItemStack.TypeClass,
) : BukkitFcGuiButton_1_7(
    inventory = inventory,
    slotIndex = slotIndex,
    locale = locale,
    textFactory = textFactory,
    textConverter = textConverter,
    tcItem = tcItem,
    tcItemStack = tcItemStack,
) {
    override fun updateItemDetails() {
        if (hideItemDetails) {
            itemStack.updateMeta {
                addItemFlags(
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_UNBREAKABLE,
                    ItemFlag.HIDE_DESTROYS,
                    ItemFlag.HIDE_PLACED_ON,
                    ItemFlag.HIDE_POTION_EFFECTS
                )
            }
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val textFactory: FcText.Factory,
        private val textConverter: FcTextConverter,
        private val tcItem: FcItem.TypeClass,
        private val tcItemStack: FcItemStack.TypeClass,
    ) : BukkitFcGuiButton.Factory {
        override fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton {
            return BukkitFcGuiButton_1_8(
                inventory = inventory,
                slotIndex = slotIndex,
                locale = locale,
                textFactory = textFactory,
                textConverter = textConverter,
                tcItem = tcItem,
                tcItemStack = tcItemStack,
            )
        }
    }
}
