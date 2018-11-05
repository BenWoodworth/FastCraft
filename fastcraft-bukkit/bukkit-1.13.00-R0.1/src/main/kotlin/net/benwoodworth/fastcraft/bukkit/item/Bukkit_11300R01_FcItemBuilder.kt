package net.benwoodworth.fastcraft.bukkit.item

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.bukkit.text.toLegacy
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.getAs
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import toRaw

@Suppress("ClassName")
class Bukkit_11300R01_FcItemBuilder : FcItemBuilder {

    private var type: Material = Material.AIR
    private var amount: Int = 1
    private var rawDisplayName: String? = null
    private var lore: List<String>? = null
    private var durability: Short? = null

    override fun type(type: FcItemType): FcItemBuilder {
        this.type = type
            .getAs<Bukkit_11300R01_FcItemType>()
            .material

        return this
    }

    override fun amount(amount: Int): FcItemBuilder {
        this.amount = amount
        return this
    }

    override fun displayName(displayName: FcText): FcItemBuilder {
        this.rawDisplayName = displayName
            .getAs<Bukkit_11300R01_FcText>()
            .toRaw()

        return this
    }

    override fun lore(lore: List<FcText>): FcItemBuilder {
        this.lore = lore
            .map { loreLine ->
                loreLine
                    .getAs<Bukkit_11300R01_FcText>()
                    .toLegacy()
            }

        return this
    }

    override fun durability(durability: Int): FcItemBuilder {
        this.durability = durability.toShort()
        return this
    }

    override fun build(): FcItem {
        val item = ItemStack(type, amount)

        durability?.let {
            item.durability = it
        }

        val metaJson = json {
            "display" to json {
                rawDisplayName?.let {
                    "Name" to JsonLiteral(it)
                }

                lore?.let { lore ->
                    "Lore" to jsonArray {
                        lore.forEach { +it }
                    }
                }
            }
        }

        @Suppress("DEPRECATION")
        Bukkit.getUnsafe().modifyItemStack(item, metaJson.toString())

        return Bukkit_11300R01_FcItem(item)
    }
}
