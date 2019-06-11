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

@AutoFactory
class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    locale: FcLocale,
    @Provided private val itemTypes: FcItemTypes,
    @Provided textFactory: FcTextFactory,
    @Provided private val textConverter: BukkitFcTextConverter
) : BukkitFcGuiButton {

    private var itemStack: ItemStack
        get() = inventory.getItem(slotIndex)
        set(value) {
            println("Slot: $slotIndex")
            inventory.setItem(slotIndex, value)
        }

    init {
        clear()
    }

    override val onClick: HandlerSet<FcGuiClickEvent> = HandlerSet()

    override var itemType: FcItemType
        get() = itemTypes.bukkit.fromMaterial(itemStack.type)
        set(value) {
            itemStack.type = value.bukkit.material
        }

    override var amount: Int
        get() = itemStack.amount
        set(value) {
            itemStack.amount = value
        }

    override var text: FcText = textFactory.createFcText()
        set(value) {
            field = value
            with(textConverter) {
                itemStack.itemMeta = itemStack.itemMeta.apply {
                    displayName = value.toLegacy(locale)
                }
            }
        }

    override var description: List<FcText> = emptyList()
        set(value) {
            field = value
            with(textConverter) {
                itemStack.itemMeta = itemStack.itemMeta.apply {
                    lore = value.map { it.toLegacy(locale) }
                }
            }
        }

    override var locale: FcLocale = locale
        set(value) {
            field = value
            text = text
            description = description
        }

    override fun clear() {
        itemStack = ItemStack(Material.AIR).apply {
            itemMeta = itemMeta.apply {
                displayName = ""
                lore = emptyList()
            }
        }
    }

    override fun copyItem(item: FcItem) {
        itemStack = item.bukkit.toItemStack()
        text = item.name
        description = item.lore
    }
}