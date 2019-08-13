package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.bukkit
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

@AutoFactory
class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: FcLocale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textConverter: BukkitFcTextConverter
) : BukkitFcGuiButton {

    private lateinit var _text: FcText
    private lateinit var _description: List<FcText>

    private lateinit var itemStack: ItemStack
    private var displayName: String? = null
    private var lore: List<String>? = null

    init {
        clear()
    }

    override val onClick: HandlerSet<FcGuiClickEvent> = HandlerSet()

    override var itemType: FcItemType
        get() = itemTypes.bukkit.fromMaterial(itemStack.type)
        set(value) {
            itemStack.type = value.bukkit.material
            updateItem()
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
            updateDisplayName()
            updateItem()
        }

    override var description: List<FcText>
        get() = _description
        set(value) {
            _description = value
            updateLore()
            updateItem()
        }

    override var locale: FcLocale = locale
        set(value) {
            field = value
            updateDisplayName()
            updateLore()
            updateItem()
        }

    private fun updateDisplayName() {
        with(textConverter) {
            displayName = _text.toLegacy(locale)
        }
    }

    private fun updateLore() {
        with(textConverter) {
            lore = _description.map { it.toLegacy(locale) }
        }
    }

    private fun updateItem() {
        itemStack.itemMeta?.let { meta ->
            meta.displayName = displayName
            meta.lore = lore

            itemStack.itemMeta = meta
        }

        inventory.setItem(slotIndex, itemStack)
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.bukkit.toItemStack()
        _text = item.name
        _description = item.lore

        updateDisplayName()
        updateLore()
        updateItem()
    }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
//        itemStack = ItemStack(Material.GOLDEN_APPLE)

        _text = textFactory.createFcText()
        _description = emptyList()

        displayName = ""
        lore = emptyList()
        updateItem()
    }
}