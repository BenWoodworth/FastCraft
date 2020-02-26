package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.material
import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import java.util.*
import kotlin.math.roundToInt
import kotlin.properties.Delegates.observable

@AutoFactory
class BukkitFcGuiButton_1_8_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: Locale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textConverter: FcTextConverter
) : BukkitFcGuiButton {
    private var hideItemDetails: Boolean = false
    private var text: FcText? = null
    private var description: List<FcText>? = null

    private var isProgressSet = false
    private var progress: Double? = null

    private var itemStack: ItemStack by observable(ItemStack(Material.AIR)) { _, _, _ ->
        updateSlot()
    }

    override var listener: FcGuiButton.Listener = FcGuiButton.Listener.Default

    override fun setItemType(itemType: FcItemType) {
        itemStack.type = itemType.material

        updateDisplayName()
        updateLore()
        updateItemDetails()
        updateSlot()
    }

    override fun setAmount(amount: Int) {
        itemStack.amount = amount
        updateSlot()
    }

    override fun setText(text: FcText) {
        this.text = text

        updateDisplayName()
        updateSlot()
    }

    override fun setDescription(description: List<FcText>) {
        this.description = description

        updateLore()
        updateSlot()
    }

    override fun setProgress(progress: Double?) {
        isProgressSet = true
        this.progress = progress

        updateDamage()
        updateSlot()
    }

    override var locale: Locale by observable(locale) { _, _, _ ->
        updateDisplayName()
        updateLore()
        updateSlot()
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.toItemStack()

        text = item.name
        description = item.lore
        hideItemDetails = false
    }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
        text = textFactory.createFcText()
        description = emptyList()
        progress = null
        hideItemDetails = false
    }

    override fun hideItemDetails() {
        hideItemDetails = true
        updateItemDetails()
        updateSlot()
    }

    private fun updateItemDetails() {
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

    private fun updateDamage() {
        if (!isProgressSet) return

        val maxDamage = itemStack.type.maxDurability.toInt()

        itemStack.updateMeta {
            if (this is Damageable) {
                damage = when (val progress = progress) {
                    null -> 0
                    in 0.0..1.0 -> ((1.0 - progress) * (maxDamage - 1) + 1).roundToInt()
                    else -> 0
                }
            }
        }
    }

    private fun updateSlot() {
        inventory.setItem(slotIndex, itemStack)
    }

    private fun updateDisplayName() {
        text?.let { text ->
            itemStack.updateMeta {
                setDisplayName(textConverter.toLegacy(text, locale))
            }
        }
    }

    private fun updateLore() {
        description?.let { description ->
            itemStack.updateMeta {
                lore = description.map {
                    textConverter.toLegacy(it, locale)
                }
            }
        }
    }
}
