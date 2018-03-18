package net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import org.apache.commons.lang.WordUtils
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcText_Bukkit_1_7_2_R0_3(
        override val text: String
) : BukkitFcText_Bukkit {

    override var color: BukkitFcTextColor_Bukkit? = null
    override var bold: Boolean? = null
    override var italic: Boolean? = null
    override var underlined: Boolean? = null
    override var strikeThrough: Boolean? = null
    override var obfuscated: Boolean? = null
    //override var shiftClickAction: BukkitFcTextAction_Bukkit.ShiftClick? = null
    //override var clickAction: BukkitFcTextAction_Bukkit.Click? = null
    //override var hoverAction: BukkitFcTextAction_Bukkit.Hover? = null
    override var extra: MutableList<BukkitFcText> = mutableListOf()

    class Factory @Inject constructor(
    ) : BukkitFcText_Bukkit.Factory {

        override fun buildText(text: String): FcText.Builder {
            return BukkitFcText_Bukkit.Builder(
                    BukkitFcText_Bukkit_1_7_2_R0_3(text)
            )
        }

        override fun getItemName(item: ItemStack): BukkitFcText {
            return BukkitFcText_Bukkit_1_7_2_R0_3(
                    WordUtils.capitalizeFully(item.data.toString())
            )
        }

        override fun getItemDisplayName(item: ItemStack): BukkitFcText? {
            return if (item.hasItemMeta()) {
                item.itemMeta.displayName?.let {
                    BukkitFcText_Bukkit_1_7_2_R0_3(it)
                }
            } else {
                null
            }
        }

        override fun setItemDisplayName(item: ItemStack, displayName: FcText?) {
            item.itemMeta.displayName = (displayName as BukkitFcText_Bukkit?)?.text
        }

        override fun getItemLore(item: ItemStack): List<BukkitFcText?>? {
            return if (item.hasItemMeta()) {
                item.itemMeta.lore?.map {
                    BukkitFcText_Bukkit_1_7_2_R0_3(it)
                }
            } else {
                null
            }
        }

        override fun setItemLore(item: ItemStack, lore: List<BukkitFcText?>?) {
            item.itemMeta.lore = lore?.map {
                (it as BukkitFcText_Bukkit?)?.text
            }
        }

        override fun sendPlayerMessage(player: Player, message: FcText) {
            player.sendMessage(
                    (message as BukkitFcText_Bukkit).text
            )
        }
    }
}