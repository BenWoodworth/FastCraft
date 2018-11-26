package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.util.invoke
import org.bukkit.inventory.ItemStack
import javax.inject.Provider

@AutoFactory
@Suppress("ClassName")
class Bukkit_11300R01_FcItem(
    private val itemStack: ItemStack,

    @Provided private val buildText: Provider<FcTextBuilder>
) : FcItem {

    override val type: Bukkit_11300R01_FcItemType
        get() = Bukkit_11300R01_FcItemType(itemStack.type, buildText)

    override val amount: Int
        get() = itemStack.amount

    override val displayName: FcText
        get() = itemStack.displayName
            ?.let { buildText { text = it } }
            ?: buildText { translate = type.name }

    override val lore: List<FcText>
        get() = itemStack.lore
            ?.map { buildText { text = it } }
            ?: emptyList()

    override val maxAmount: Int
        get() = itemStack.maxStackSize

    fun toItemStack(): ItemStack {
        return itemStack.clone()
    }

    override fun buildCopy(build: FcItemBuilder.() -> Unit): FcItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
