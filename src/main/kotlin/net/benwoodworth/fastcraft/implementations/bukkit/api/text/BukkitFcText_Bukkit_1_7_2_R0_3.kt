package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import org.apache.commons.lang.WordUtils
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcText_Bukkit_1_7_2_R0_3(
        override val text: String
) : BukkitFcText.Legacy {

    override var color: BukkitFcTextColor? = null
    override var bold: Boolean? = null
    override var italic: Boolean? = null
    override var underlined: Boolean? = null
    override var strikeThrough: Boolean? = null
    override var obfuscated: Boolean? = null
    override var shiftClickAction: BukkitFcTextAction.ShiftClick? = null
    override var clickAction: BukkitFcTextAction.Click? = null
    override var hoverAction: BukkitFcTextAction.Hover? = null
    override var extra: MutableList<BukkitFcText> = mutableListOf()

    class Builder @Inject constructor(
    ) : BukkitFcText.Builder {

        override fun setText(text: String): FcText.Builder.Specific {
            return BukkitFcText.Builder.Specific(BukkitFcText_Bukkit_1_7_2_R0_3(text))
        }
    }

    class Helper @Inject constructor(
    ) : BukkitFcText.Helper {

        override fun getItemName(item: ItemStack): BukkitFcText {
            // TODO Use item.itemMeta.localizedName
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
            item.itemMeta.displayName = (displayName as BukkitFcText.Legacy?)?.text
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
                (it as BukkitFcText.Legacy?)?.text
            }
        }

        override fun sendPlayerMessage(player: Player, message: FcText) {
            player.sendMessage(
                    (message as BukkitFcText.Legacy).text
            )
        }
    }
}