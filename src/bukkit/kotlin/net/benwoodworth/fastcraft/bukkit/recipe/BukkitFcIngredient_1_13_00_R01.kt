package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemFactory
import net.benwoodworth.fastcraft.bukkit.item.ItemStackNameProvider
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack

@AutoFactory
class BukkitFcIngredient_1_13_00_R01(
    val ingredient: ItemStack,
    @Provided val textFactory: FcTextFactory,
    @Provided val itemFactory: BukkitFcItemFactory,
    @Provided val itemStackNameProvider: ItemStackNameProvider
) : BukkitFcIngredient {
    private companion object {
        const val WILDCARD_DATA = (-1).toByte()
    }

    @Suppress("DEPRECATION")
    private val hasWildcardData: Boolean = ingredient.data.data == WILDCARD_DATA

    override val name: FcText = run {
        with(textFactory) {
            val itemName = with(itemStackNameProvider) {
                ingredient.getName()
            }

            return@run if (!hasWildcardData) {
                itemName
            } else {
                createFcText(
                    extra = listOf(
                        itemName,
                        createFcText(
                            text = " (*)"
                        )
                    )
                )
            }
        }
    }

    override fun matches(item: FcItem): Boolean {
        val itemStack = item.bukkit.toItemStack()

        @Suppress("DEPRECATION")
        if (hasWildcardData) {
            itemStack.data.data = WILDCARD_DATA
        }

        return ingredient.isSimilar(itemStack)
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_13_00_R01
                && other.ingredient == ingredient
    }

    override fun hashCode(): Int {
        return ingredient.hashCode()
    }
}
