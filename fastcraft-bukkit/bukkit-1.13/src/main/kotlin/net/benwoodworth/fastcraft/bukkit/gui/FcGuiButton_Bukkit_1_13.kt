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

open class FcGuiButton_Bukkit_1_13(
    inventory: Inventory,
    slotIndex: Int,
    locale: Locale,
    fcTextFactory: FcText.Factory,
    fcTextConverter: FcTextConverter,
    fcItemOperations: FcItem.Operations,
    fcItemStackOperations: FcItemStack.Operations,
) : FcGuiButton_Bukkit_1_8(
    inventory = inventory,
    slotIndex = slotIndex,
    locale = locale,
    fcTextFactory = fcTextFactory,
    fcTextConverter = fcTextConverter,
    fcItemOperations = fcItemOperations,
    fcItemStackOperations = fcItemStackOperations,
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
        private val fcTextFactory: FcText.Factory,
        private val fcTextConverter: FcTextConverter,
        private val fcItemOperations: FcItem.Operations,
        private val fcItemStackOperations: FcItemStack.Operations,
    ) : FcGuiButton_Bukkit.Factory {
        override fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton {
            return FcGuiButton_Bukkit_1_13(
                inventory = inventory,
                slotIndex = slotIndex,
                locale = locale,
                fcTextFactory = fcTextFactory,
                fcTextConverter = fcTextConverter,
                fcItemOperations = fcItemOperations,
                fcItemStackOperations = fcItemStackOperations,
            )
        }
    }
}
