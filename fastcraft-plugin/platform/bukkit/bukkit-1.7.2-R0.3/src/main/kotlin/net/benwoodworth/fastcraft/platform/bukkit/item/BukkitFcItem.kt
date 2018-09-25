package net.benwoodworth.fastcraft.platform.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.api.item.FcItem
import net.benwoodworth.fastcraft.platform.api.item.FcItemType
import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.inventory.ItemStack

/**
 * Bukkit implementation of [FcItem].
 *
 * @property base The item being adapter. Should not be modified.
 */
@AutoFactory
class BukkitFcItem(
        private val base: ItemStack,

        @Provided itemMutableFactory: BukkitFcItem_MutableFactory
) : FcItem by itemMutableFactory.create(base) {

    @AutoFactory
    class Mutable(
            override val base: ItemStack,

            @Provided private val itemFactory: BukkitFcItemFactory,
            @Provided private val itemMutableFactory: BukkitFcItem_MutableFactory,
            @Provided private val textFactory: BukkitFcText.Factory
    ) : FcItem.Mutable, Adapter<ItemStack>() {

        override val type: FcItemType
            get() = BukkitFcItemType(base.type)

        override var amount: Int
            get() = base.amount
            set(value) {
                base.amount = value
            }

        override val name: FcText
            get() = textFactory.getItemName(base)

        override var displayName: FcText?
            get() = textFactory.getItemDisplayName(base)
            set(value) = textFactory.setItemDisplayName(base, value)

        override var lore: List<FcText?>?
            get() = textFactory.getItemLore(base)
            set(value) = textFactory.setItemLore(base, value)

        override val maxStackSize: Int
            get() = base.maxStackSize

        override var durability: Int
            get() = base.durability.toInt()
            set(value) {
                base.durability = value.toShort()
            }

        override fun isSimilar(item: FcItem): Boolean {
            return base.isSimilar((item as BukkitFcItem).base)
        }

        override fun mutableCopy(): FcItem.Mutable {
            return itemMutableFactory.create(base)
        }

        override fun immutableCopy(): FcItem {
            return itemFactory.create(base.clone())
        }
    }
}
