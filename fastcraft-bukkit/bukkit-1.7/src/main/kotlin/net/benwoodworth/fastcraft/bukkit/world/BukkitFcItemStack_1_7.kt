package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcItemStack_1_7(
    override val bukkitItemStack: ItemStack,
    protected val items: FcItem.Factory,
    protected val textFactory: FcText.Factory,
) : BukkitFcItemStack {
    override val type: FcItem
        get() = items.fromMaterialData(bukkitItemStack.data)

    override val amount: Int
        get() = bukkitItemStack.amount

    override val name: FcText
        get() = bukkitItemStack
            .takeIf { it.hasItemMeta() }
            ?.itemMeta
            ?.takeIf { it.hasDisplayName() }
            ?.displayName
            ?.let { textFactory.create(it) }
            ?: type.blockName

    override val lore: List<FcText>
        get() = bukkitItemStack
            .takeIf { it.hasItemMeta() }
            ?.itemMeta
            ?.takeIf { it.hasLore() }
            ?.lore
            ?.map { textFactory.create(it ?: "") }
            ?: emptyList()

    override val hasMetadata: Boolean
        get() = bukkitItemStack.hasItemMeta()

    override fun toBukkitItemStack(): ItemStack {
        return bukkitItemStack.clone()
    }

    override fun equals(other: Any?): Boolean {
        return other is FcItemStack && bukkitItemStack == other.bukkitItemStack
    }

    override fun hashCode(): Int {
        return bukkitItemStack.hashCode()
    }

    @Singleton
    open class Factory @Inject constructor(
        protected val items: FcItem.Factory,
        protected val textFactory: FcText.Factory,
        protected val server: Server,
    ) : BukkitFcItemStack.Factory {
        override fun create(item: FcItem, amount: Int): FcItemStack {
            return create(item.toItemStack(amount))
        }

        override fun copyItem(itemStack: FcItemStack, amount: Int): FcItemStack {
            try {
                if (amount == itemStack.amount) {
                    return itemStack
                }

                val bukkitItemStack = itemStack.toBukkitItemStack()
                bukkitItemStack.amount = amount

                return create(bukkitItemStack)
            } catch (e: AssertionError) {
                System.err.println("""
                    Share this with Kepler:
                    - itemStack = ${itemStack.bukkitItemStack}
                    - amount = $amount
                """.trimIndent())
                throw e
            }
        }

        override fun parseOrNull(itemStr: String, amount: Int): FcItemStack? {
            val materialId: String
            val data: String?

            when (val dataIndex = itemStr.indexOf('{')) {
                -1 -> {
                    materialId = itemStr
                    data = null
                }
                else -> {
                    materialId = itemStr.substring(0 until dataIndex)
                    data = itemStr.substring(dataIndex)
                }
            }

            val item = items.parseOrNull(materialId) ?: return null
            val itemStack = item.toItemStack(amount)

            if (data != null) {
                @Suppress("DEPRECATION")
                server.unsafe.modifyItemStack(itemStack, data)
            }

            return create(itemStack)
        }

        override fun create(itemStack: ItemStack): FcItemStack {
            return BukkitFcItemStack_1_7(
                bukkitItemStack = itemStack,
                items = items,
                textFactory = textFactory
            )
        }
    }
}
