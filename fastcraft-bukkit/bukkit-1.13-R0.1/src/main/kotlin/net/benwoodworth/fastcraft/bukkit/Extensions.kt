@file:Suppress("NOTHING_TO_INLINE")

package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilderTyped
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemBuilderTyped
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemType
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.lang.StringBuilder

internal inline val FcItem.bukkitItemStack
    get() = (this as net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem).`bukkitItemStack$1_13_R0_1` as ItemStack

internal inline val FcItemBuilderTyped.bukkitItemStack
    get() = (this as net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemBuilderTyped).`bukkitItemStack$1_13_R0_1` as ItemStack

internal inline val FcPlayer.bukkitPlayer
    get() = (this as net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer).`bukkitPlayer$1_13_R0_1` as Player

internal inline val FcText.bukkitLegacyText
    get() = (this as net.benwoodworth.fastcraft.bukkit.text.BukkitFcText).`bukkitLegacyText$1_13_R0_1`

internal inline val FcText.bukkitRawText
    get() = (this as net.benwoodworth.fastcraft.bukkit.text.BukkitFcText).`bukkitRawText$1_13_R0_1`

internal inline fun FcText.bukkitAppendRawText(stringBuilder: StringBuilder) {
    (this as net.benwoodworth.fastcraft.bukkit.text.BukkitFcText).`bukkitAppendRawText$1_13_R0_1`(stringBuilder)
}

internal inline val FcItemType.bukkitMaterial
    get() = (this as net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemType).`bukkitMaterial$1_13_R0_1` as Material

internal inline val FcTextColor.bukkitColorName
    get() = (this as net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor).`bukkitColorName$1_13_r0_1`

internal inline val FcTextColor.bukkitColorId
    get() = (this as net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor).`bukkitColorCode$1_13_r0_1`



internal inline fun ItemStack.updateItemMeta(update: (itemMeta: ItemMeta) -> Unit) {
    val meta = this.itemMeta
    update(meta)
    this.itemMeta = meta
}