package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.server.permission
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.toRaw
import net.benwoodworth.fastcraft.bukkit.world.BukkitFcItemStack
import net.benwoodworth.fastcraft.bukkit.world.bukkit
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.localeconfig.api.LocaleApi
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcPlayer_1_7 {
    @Singleton
    class Operations @Inject constructor(
        private val fcTextConverter: FcTextConverter,
        private val server: Server,
        private val fcPlayerInventoryFactory: BukkitFcPlayerInventory_1_7.Factory,
        fcSoundOperations: FcSound.Operations,
        fcItemStackOperations: FcItemStack.Operations,
    ) : BukkitFcPlayer.Operations,
        BukkitFcSound.Operations by fcSoundOperations.bukkit,
        BukkitFcItemStack.Operations by fcItemStackOperations.bukkit {

        override val FcPlayer.player: Player
            get() = value as Player

        override val FcPlayer.username: String
            get() = player.name

        override var FcPlayer.customName: String?
            get() = player.customName
            set(value) {
                player.customName = value
            }

        override val FcPlayer.uuid: UUID
            get() = player.uniqueId

        override val FcPlayer.locale: Locale
            get() = LocaleApi.getLocale(player) ?: Locale.ENGLISH // TODO Don't default to English

        override val FcPlayer.isOnline: Boolean
            get() = player.isOnline

        override val FcPlayer.inventory: FcPlayerInventory
            get() = fcPlayerInventoryFactory.create(player.inventory)

        override fun FcPlayer.sendMessage(message: FcText) {
            message as BukkitFcText
            when (message) {
                is BukkitFcText.Legacy -> {
                    player.sendMessage(message.legacyText)
                }
                is BukkitFcText.Component -> {
                    server.dispatchCommand(
                        server.consoleSender,
                        "tellraw $username ${fcTextConverter.toRaw(message)}"
                    )
                }
            }
        }

        override fun FcPlayer.hasPermission(permission: FcPermission): Boolean {
            return player.hasPermission(permission.permission)
        }

        override fun FcPlayer.giveItems(items: List<FcItemStack>, dropAll: Boolean) {
            fun ItemStack.drop() {
                player.world.dropItemNaturally(player.location, this)
            }

            items.forEach { itemStack ->
                if (dropAll) {
                    itemStack.itemStack.clone().drop()
                } else {
                    val notAdded = player.inventory.addItem(itemStack.itemStack).values
                    notAdded.forEach { it.drop() }
                }
            }
        }

        override fun FcPlayer.openCraftingTable() {
            player.openWorkbench(null, true)
        }

        override fun FcPlayer.playSound(sound: FcSound, volume: Double, pitch: Double) {
            player.playSound(
                player.location,
                sound.sound,
                volume.toFloat(),
                pitch.toFloat(),
            )
        }

        override fun FcPlayer.executeCommand(command: String) {
            player.performCommand(command)
        }
    }

    @Singleton
    class Provider @Inject constructor(
        private val server: Server,
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
            return FcPlayer(player)
        }
    }
}
