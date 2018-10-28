package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.`BukkitFcText$1_13_R0_1`
import net.benwoodworth.fastcraft.bukkit.updateMeta
import net.benwoodworth.fastcraft.platform.item.FcItemBuilderTyped
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.`as`
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
class `BukkitFcItemBuilderTyped$1_13_R0_1`(
    val bukkitItemStack: ItemStack
) : FcItemBuilderTyped {

    override fun build(): `BukkitFcItem$1_13_R0_1` {
        return `BukkitFcItem$1_13_R0_1`(bukkitItemStack.clone())
    }

    override fun amount(amount: Int): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        bukkitItemStack.amount = amount
        return this
    }

    override fun displayName(displayName: FcText?): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        val bukkitText = displayName?.`as`<`BukkitFcText$1_13_R0_1`>()

        bukkitItemStack.updateMeta {
            this.displayName = bukkitText?.legacyText(TODO())
        }

        return this
    }

    override fun lore(lore: List<FcText>?): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        bukkitItemStack.updateMeta {
            this.lore = lore?.map {
                val bukkitText = it.`as`<`BukkitFcText$1_13_R0_1`>()

                bukkitText.legacyText(TODO())
            }
        }

        return this
    }

    override fun durability(durability: Int): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        bukkitItemStack.durability = durability.toShort()
        return this
    }
}
