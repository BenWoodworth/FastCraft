package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.meta.Damageable
import java.util.*
import javax.inject.Inject

open class BukkitFcGuiButton_1_13_R01(
    inventory: Inventory,
    slotIndex: Int,
    locale: Locale,
    textFactory: FcTextFactory,
    textConverter: FcTextConverter
) : BukkitFcGuiButton_1_8_R01(
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
            return BukkitFcGuiButton_1_13_R01(
                inventory = inventory,
                slotIndex = slotIndex,
                locale = locale,
                textFactory = textFactory,
                textConverter = textConverter
            )
        }
    }

    override fun updateDamage() {
        if (!isProgressSet) return

        val maxDurability = itemStack.type.maxDurability.toInt()

        itemStack.updateMeta {
            if (this is Damageable) {
                damage = calculateDurability(_progress, maxDurability)
            }
        }
    }
}
