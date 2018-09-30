package net.benwoodworth.fastcraft.platform.bukkit.item

import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.bukkit.bukkitItemStack
import net.benwoodworth.fastcraft.platform.bukkit.bukkitLegacyText
import net.benwoodworth.fastcraft.platform.bukkit.updateItemMeta
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
inline class `BukkitFcItemBuilderTyped$1_13_R0_1`(
    override val `bukkitItemStack$1_13_R0_1`: ItemStack
) : BukkitFcItemBuilderTyped {

    override fun build(): `BukkitFcItem$1_13_R0_1` {
        return `BukkitFcItem$1_13_R0_1`(bukkitItemStack.clone())
    }

    override fun amount(amount: Int): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        bukkitItemStack.amount = amount
        return this
    }

    override fun displayName(displayName: FcText?): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        `bukkitItemStack$1_13_R0_1`.updateItemMeta { itemMeta ->
            itemMeta.displayName = displayName?.bukkitLegacyText
        }

        return this
    }

    override fun lore(lore: List<FcText>?): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        `bukkitItemStack$1_13_R0_1`.updateItemMeta { itemMeta ->
            itemMeta.lore = lore?.map {
                it.bukkitLegacyText
            }
        }

        return this
    }

    override fun durability(durability: Int): `BukkitFcItemBuilderTyped$1_13_R0_1` {
        `bukkitItemStack$1_13_R0_1`.durability = durability.toShort()
        return this
    }
}
