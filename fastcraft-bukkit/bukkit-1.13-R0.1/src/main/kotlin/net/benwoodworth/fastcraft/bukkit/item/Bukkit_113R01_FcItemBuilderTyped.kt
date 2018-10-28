package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_113R01_FcText
import net.benwoodworth.fastcraft.bukkit.updateMeta
import net.benwoodworth.fastcraft.platform.item.FcItemBuilderTyped
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.`as`
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
class Bukkit_113R01_FcItemBuilderTyped(
    val bukkitItemStack: ItemStack
) : FcItemBuilderTyped {

    override fun build(): Bukkit_113R01_FcItem {
        return Bukkit_113R01_FcItem(bukkitItemStack.clone())
    }

    override fun amount(amount: Int): Bukkit_113R01_FcItemBuilderTyped {
        bukkitItemStack.amount = amount
        return this
    }

    override fun displayName(displayName: FcText?): Bukkit_113R01_FcItemBuilderTyped {
        val bukkitText = displayName?.`as`<Bukkit_113R01_FcText>()

        bukkitItemStack.updateMeta {
            this.displayName = bukkitText?.legacyText(TODO())
        }

        return this
    }

    override fun lore(lore: List<FcText>?): Bukkit_113R01_FcItemBuilderTyped {
        bukkitItemStack.updateMeta {
            this.lore = lore?.map {
                val bukkitText = it.`as`<Bukkit_113R01_FcText>()

                bukkitText.legacyText(TODO())
            }
        }

        return this
    }

    override fun durability(durability: Int): Bukkit_113R01_FcItemBuilderTyped {
        bukkitItemStack.durability = durability.toShort()
        return this
    }
}
