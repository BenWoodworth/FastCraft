package net.benwoodworth.fastcraft.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.toRaw
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.localeconfig.api.LocaleApi
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

@AutoFactory
class BukkitFcPlayer_1_7_5_R01(
    override val player: Player,
    @Provided private val textConverter: FcTextConverter,
    @Provided private val server: Server,
    @Provided private val playerInventoryFactory: BukkitFcPlayerInventory_1_7_5_R01Factory,
) : BukkitFcPlayer {
    override val username: String
        get() = player.name

    override var customName: String?
        get() = player.customName
        set(value) {
            player.customName = value
        }

    override val uuid: UUID
        get() = player.uniqueId

    override val locale: Locale
        get() = LocaleApi.getLocale(player)

    override val isOnline: Boolean
        get() = player.isOnline

    override val inventory: FcPlayerInventory
        get() = playerInventoryFactory.create(player.inventory)

    override fun sendMessage(message: FcText) {
        message as BukkitFcText
        when (message) {
            is BukkitFcText.Legacy -> {
                player.sendMessage(message.legacyText)
            }
            is BukkitFcText.Component -> {
                server.dispatchCommand(
                    server.consoleSender,
                    "tellraw $username ${textConverter.toRaw(message)}"
                )
            }
        }
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }

    override fun giveItems(items: List<FcItem>, dropAll: Boolean) {
        fun ItemStack.drop() {
            player.world.dropItemNaturally(player.location, this)
        }

        items.forEach { item ->
            if (dropAll) {
                item.toItemStack().drop()
            } else {
                val notAdded = player.inventory.addItem(item.toItemStack()).values
                notAdded.forEach { it.drop() }
            }
        }
    }

    override fun openCraftingTable() {
        player.openWorkbench(null, true)
    }

    override fun equals(other: Any?): Boolean {
        return other is FcPlayer && player == other.player
    }

    override fun hashCode(): Int {
        return player.hashCode()
    }
}
