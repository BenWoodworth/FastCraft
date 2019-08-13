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
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

@AutoFactory
class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: FcLocale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textConverter: BukkitFcTextConverter,
    @Provided private val itemFactory: ItemFactory
) : BukkitFcGuiButton {

    private lateinit var _text: FcText
    private lateinit var _description: List<FcText>

    private lateinit var itemStack: ItemStack
    private lateinit var itemMeta: ItemMeta

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
            itemMeta.displayName = _text.toLegacy()
            updateItem()
        }

    override var description: List<FcText>
        get() = _description
        set(value) {
            _description = value
            itemMeta.lore = _description.toLegacy()
            updateItem()
        }

    override var locale: FcLocale = locale
        set(value) {
            field = value
            itemMeta.displayName = _text.toLegacy()
            itemMeta.lore = _description.toLegacy()
            updateItem()
        }

    private fun FcText.toLegacy(): String {
        with(textConverter) {
            return this@toLegacy.toLegacy(locale)
        }
    }

    private fun List<FcText>.toLegacy(): List<String> {
        with(textConverter) {
            return this@toLegacy.map { it.toLegacy(locale) }
        }
    }

    private fun updateItem() {
        itemStack.itemMeta = itemMeta
        inventory.setItem(slotIndex, itemStack)
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.bukkit.toItemStack()
        itemMeta = itemStack.itemMeta ?: itemFactory.getItemMeta(Material.STONE)

        _text = item.name
        _description = item.lore

        updateItem()
    }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
        itemMeta = itemFactory.getItemMeta(Material.STONE)

        _text = textFactory.createFcText()
        _description = emptyList()

        updateItem()
    }
}