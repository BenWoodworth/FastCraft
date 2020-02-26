package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.item.material
import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.util.updateMeta
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.properties.Delegates.observable

open class BukkitFcGuiButton_1_8_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: Locale,
    private val textFactory: FcTextFactory,
    private val textConverter: FcTextConverter
) : BukkitFcGuiButton {
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

    protected var hideItemDetails: Boolean = false
    protected var _text: FcText? = null
    protected var _description: List<FcText>? = null

    protected var isProgressSet = false
    protected var _progress: Double? = null

    protected var itemStack: ItemStack by observable(ItemStack(Material.AIR)) { _, _, _ ->
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
        this._text = text

        updateDisplayName()
        updateSlot()
    }

    override fun setDescription(description: List<FcText>) {
        this._description = description

        updateLore()
        updateSlot()
    }

    override fun setProgress(progress: Double?) {
        isProgressSet = true
        this._progress = progress

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

        _text = item.name
        _description = item.lore
        hideItemDetails = false
    }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
        _text = textFactory.createFcText()
        _description = emptyList()
        _progress = null
        hideItemDetails = false
    }

    override fun hideItemDetails() {
        hideItemDetails = true
        updateItemDetails()
        updateSlot()
    }

    protected open fun updateItemDetails() {
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

    protected fun calculateDurability(progress: Double?, maxDurability: Int): Int {
        return when (progress) {
            null -> 0
            in 0.0..1.0 -> ((1.0 - progress) * (maxDurability - 1) + 1).roundToInt()
            else -> 0
        }
    }

    protected open fun updateDamage() {
        if (!isProgressSet) return

        val maxDurability = itemStack.type.maxDurability.toInt()
        itemStack.durability = calculateDurability(_progress, maxDurability).toShort()
    }

    protected open fun updateSlot() {
        inventory.setItem(slotIndex, itemStack)
    }

    protected open fun updateDisplayName() {
        _text?.let { text ->
            itemStack.updateMeta {
                setDisplayName(textConverter.toLegacy(text, locale))
            }
        }
    }

    protected open fun updateLore() {
        _description?.let { description ->
            itemStack.updateMeta {
                lore = description.map {
                    textConverter.toLegacy(it, locale)
                }
            }
        }
    }
}
