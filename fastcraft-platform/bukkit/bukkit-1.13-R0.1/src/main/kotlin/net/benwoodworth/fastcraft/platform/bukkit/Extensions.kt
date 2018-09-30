package net.benwoodworth.fastcraft.platform.bukkit

import net.benwoodworth.fastcraft.platform.api.item.FcItem
import net.benwoodworth.fastcraft.platform.api.item.FcItemType
import net.benwoodworth.fastcraft.platform.api.player.FcPlayer
import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.bukkit.item.BukkitFcItem
import net.benwoodworth.fastcraft.platform.bukkit.item.BukkitFcItemType
import net.benwoodworth.fastcraft.platform.bukkit.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

internal inline val FcItem.bukkitItem
    get() = (this as BukkitFcItem).`bukkitItem$1_13_R0_1` as ItemStack

internal inline val FcPlayer.bukkitPlayer
    get() = (this as BukkitFcPlayer).`bukkitPlayer$1_13_R0_1` as Player

internal inline val FcText.bukkitLegacyText
    get() = (this as BukkitFcText).`bukkitLegacyText$1_13_R0_1`

internal inline val FcText.bukkitRawText
    get() = (this as BukkitFcText).`bukkitRawText$1_13_R0_1`

internal inline val FcItemType.bukkitItemType
    get() = (this as BukkitFcItemType).`bukkitItemType$1_13_R0_1` as Material