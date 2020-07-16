package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.meta.Damageable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcGuiButton_1_13(
    inventory: Inventory,
    slotIndex: Int,
    locale: Locale,
    textFactory: FcText.Factory,
    textConverter: FcTextConverter,
    fcItemTypeClass: FcItem.TypeClass,
    fcItemStackTypeClass: FcItemStack.TypeClass,
) : BukkitFcGuiButton_1_8(
    inventory = inventory,
    slotIndex = slotIndex,
    locale = locale,
    textFactory = textFactory,
    textConverter = textConverter,
    fcItemTypeClass = fcItemTypeClass,
    fcItemStackTypeClass = fcItemStackTypeClass,
) {
    override fun updateDamage() {
        if (!isProgressSet) return

        val maxDurability = itemStack.type.maxDurability.toInt()

        itemStack.updateMeta {
            if (this is Damageable) {
                damage = calculateDurability(_progress, maxDurability)
            }
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val textFactory: FcText.Factory,
        private val textConverter: FcTextConverter,
        private val fcItemTypeClass: FcItem.TypeClass,
        private val fcItemStackTypeClass: FcItemStack.TypeClass,
    ) : BukkitFcGuiButton.Factory {
        override fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton {
            return BukkitFcGuiButton_1_13(
                inventory = inventory,
                slotIndex = slotIndex,
                locale = locale,
                textFactory = textFactory,
                textConverter = textConverter,
                fcItemTypeClass = fcItemTypeClass,
                fcItemStackTypeClass = fcItemStackTypeClass,
            )
        }
    }
}
