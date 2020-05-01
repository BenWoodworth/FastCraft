package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*

interface FcPlayer {
    val username: String
    var customName: String?
    val uuid: UUID
    val locale: Locale
    val isOnline: Boolean
    val inventory: FcPlayerInventory

    fun sendMessage(message: FcText)

    fun hasPermission(permission: String): Boolean

    fun giveItems(items: List<FcItem>, dropAll: Boolean)

    fun openCraftingTable() // TODO Location

    fun playSound(sound: FcSound, volume: Double = 1.0, pitch: Double = 1.0)

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Provider {
        fun getOnlinePlayers(): List<FcPlayer>

        fun getPlayer(uuid: UUID): FcPlayer?
    }
}
