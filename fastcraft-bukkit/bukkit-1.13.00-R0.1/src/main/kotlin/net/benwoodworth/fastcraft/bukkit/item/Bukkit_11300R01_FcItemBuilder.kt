package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.util.getAs
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Provider

@Suppress("ClassName")
class Bukkit_11300R01_FcItemBuilder @Inject constructor(
    private val itemFactory: Bukkit_11300R01_FcItemFactory
) : FcItemBuilder, FcItemBuilder.WithType {

    private var itemStack = ItemStack(Material.AIR)

    override fun copyFrom(value: FcItem): FcItemBuilder.WithType {
        itemStack = value
            .getAs<Bukkit_11300R01_FcItem>()
            .getItemStackCopy()

        return this
    }

    override fun type(value: FcItemType): FcItemBuilder.WithType {
        itemStack.type = value
            .getAs<Bukkit_11300R01_FcItemType>()
            .material

        return this
    }

    override fun amount(value: Int): FcItemBuilder.WithType {
        itemStack.amount = value
        return this
    }

    override fun displayName(value: FcText?): FcItemBuilder.WithType {
        itemStack.displayName = value
            ?.getAs<Bukkit_11300R01_FcText>()
            ?.toLegacy()

        return this
    }

    override fun lore(value: List<FcText>): FcItemBuilder.WithType {
        itemStack.lore = value
            .takeUnless { it.isEmpty() }
            ?.map { it.getAs<Bukkit_11300R01_FcText>().toLegacy() }

        return this
    }

    override fun build(): FcItem {
        return itemFactory.create(itemStack.clone())
    }
}
