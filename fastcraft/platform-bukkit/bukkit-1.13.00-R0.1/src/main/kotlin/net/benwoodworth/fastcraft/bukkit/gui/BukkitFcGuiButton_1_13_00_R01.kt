package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemFactory
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

@AutoFactory
class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: FcLocale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textConverter: BukkitFcTextConverter
) : BukkitFcGuiButton {

    private lateinit var itemStack: ItemStack
    private lateinit var _type: FcItemType
    private lateinit var _text: FcText
    private lateinit var _description: List<FcText>

    init {
        clear()
    }

    override val onClick: HandlerSet<FcGuiClickEvent> = HandlerSet()

    override var itemType: FcItemType
        get() = _type
        set(value) {
            _type = value

            val hadMeta = itemStack.hasItemMeta()
            itemStack.type = value.bukkit.material
            if (!hadMeta && itemStack.hasItemMeta()) {
                updateText()
                updateDescription()
            }

            inventory.setItem(slotIndex, itemStack)
        }

    override var amount: Int
        get() = itemStack.amount
        set(value) {
            itemStack.amount = value
        }

    override var text: FcText
        get() = _text
        set(value) {
            _text = value
            updateText()
        }

    override var description: List<FcText>
        get() = _description
        set(value) {
            _description = value
            updateDescription()
        }

    private inline fun ItemStack.updateMeta(update: ItemMeta.() -> Unit) {
        if (hasItemMeta()) {
            itemMeta = itemMeta.apply {
                update(this)
            }
        }
    }

    private fun updateText() {
        with(textConverter) {
            itemStack.updateMeta {
                displayName = _text.toLegacy(locale)
            }
        }
    }

    private fun updateDescription() {
        with(textConverter) {
            itemStack.updateMeta {
                lore = _description.map { it.toLegacy(locale) }
            }
        }
    }

    override var locale: FcLocale = locale
        set(value) {
            field = value
            updateText()
            updateDescription()
        }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
        _type = itemTypes.air
        _text = textFactory.createFcText()
        _description = emptyList()

        inventory.setItem(slotIndex, itemStack)
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.bukkit.toItemStack()
        _type = item.type
        _text = item.name
        _description = item.lore

        inventory.setItem(slotIndex, itemStack)
    }
}