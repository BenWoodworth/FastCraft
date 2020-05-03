package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.toRaw
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.localeconfig.api.LocaleApi
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject

class BukkitFcPlayer_1_7(
    override val player: Player,
    private val textConverter: FcTextConverter,
    private val server: Server,
    private val playerInventoryFactory: BukkitFcPlayerInventory_1_7Factory,
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
        get() = LocaleApi.getLocale(player) ?: Locale.ENGLISH // TODO Don't default to English

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

    override fun playSound(sound: FcSound, volume: Double, pitch: Double) {
        player.playSound(player.location, sound.sound, volume.toFloat(), pitch.toFloat())
    }

    override fun equals(other: Any?): Boolean {
        return other is FcPlayer && player == other.player
    }

    override fun hashCode(): Int {
        return player.hashCode()
    }

    class Provider @Inject constructor(
        private val server: Server,
        private val textConverter: FcTextConverter,
        private val playerInventoryFactory: BukkitFcPlayerInventory_1_7Factory,
    ) : BukkitFcPlayer.Provider {
        override fun getOnlinePlayers(): List<FcPlayer> {
            return server.onlinePlayers.map { player ->
                getPlayer(player)
            }
        }

        override fun getPlayer(uuid: UUID): FcPlayer? {
            return server.getPlayer(uuid)?.let { player ->
                getPlayer(player)
            }
        }

        override fun getPlayer(player: Player): FcPlayer {
            return BukkitFcPlayer_1_7(
                player = player,
                textConverter = textConverter,
                server = server,
                playerInventoryFactory = playerInventoryFactory,
            )
        }
    }
}
