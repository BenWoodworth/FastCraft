package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import java.util.*
import javax.inject.Inject

open class BukkitFcGuiButton_1_8_R01(
    inventory: Inventory,
    slotIndex: Int,
    locale: Locale,
    textFactory: FcTextFactory,
    textConverter: FcTextConverter
) : BukkitFcGuiButton_1_7_5_R01(
    inventory = inventory,
    slotIndex = slotIndex,
    locale = locale,
    textFactory = textFactory,
    textConverter = textConverter
) {
    class Factory @Inject constructor(
        private val textFactory: FcTextFactory,
        private val textConverter: FcTextConverter
    ) : BukkitFcGuiButton.Factory {
        override fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton {
            return BukkitFcGuiButton_1_8_R01(
                inventory = inventory,
                slotIndex = slotIndex,
                locale = locale,
                textFactory = textFactory,
                textConverter = textConverter
            )
        }
    }

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
}
