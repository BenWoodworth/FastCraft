package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.fromMaterial
import net.benwoodworth.fastcraft.bukkit.item.material
import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

@AutoFactory
class BukkitFcGuiButton_1_15_00_R01(
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

    override var listener: FcGuiButton.Listener = FcGuiButton.Listener.Default

    override var itemType: FcItemType
        get() = itemTypes.fromMaterial(itemStack.type)
        set(value) {
            itemStack.type = value.material
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
            itemMeta.setDisplayName(text.toLegacy())
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
            itemMeta.setDisplayName(_text.toLegacy())
            itemMeta.lore = _description.toLegacy()
            updateItem()
        }

    private fun FcText.toLegacy(): String {
        return textConverter.toLegacy(this, locale)
    }

    private fun List<FcText>.toLegacy(): List<String> {
        return map {
            textConverter.toLegacy(it, locale)
        }
    }

    private fun updateItem() {
        itemStack.itemMeta = itemMeta
        inventory.setItem(slotIndex, itemStack)
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.toItemStack()
        itemMeta = itemStack.itemMeta ?: itemFactory.getItemMeta(Material.STONE)!!

        _text = item.name
        _description = item.lore

        updateItem()
    }

    override fun clear() {
        itemStack = ItemStack(Material.AIR)
        itemMeta = itemFactory.getItemMeta(Material.STONE)!!

        _text = textFactory.createFcText()
        _description = emptyList()

        updateItem()
    }

    override fun hideItemDetails() {
        itemMeta.addItemFlags(
            ItemFlag.HIDE_ENCHANTS,
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_UNBREAKABLE,
            ItemFlag.HIDE_DESTROYS,
            ItemFlag.HIDE_PLACED_ON,
            ItemFlag.HIDE_POTION_EFFECTS
        )
        updateItem()
    }
}
