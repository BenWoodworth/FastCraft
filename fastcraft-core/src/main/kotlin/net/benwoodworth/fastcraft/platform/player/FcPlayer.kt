package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import java.util.*

inline class FcPlayer(val value: Any) {
    interface TypeClass {
        val FcPlayer.username: String
        var FcPlayer.customName: String?
        val FcPlayer.uuid: UUID
        val FcPlayer.locale: Locale
        val FcPlayer.isOnline: Boolean
        val FcPlayer.inventory: FcPlayerInventory

        fun FcPlayer.sendMessage(message: FcText)

        fun FcPlayer.hasPermission(permission: FcPermission): Boolean

        fun FcPlayer.giveItems(items: List<FcItemStack>, dropAll: Boolean)

        fun FcPlayer.openCraftingTable() // TODO Location

        fun FcPlayer.playSound(sound: FcSound, volume: Double = 1.0, pitch: Double = 1.0)
    }

    interface Provider {
        fun getOnlinePlayers(): List<FcPlayer>

        fun getPlayer(uuid: UUID): FcPlayer?
    }
}
