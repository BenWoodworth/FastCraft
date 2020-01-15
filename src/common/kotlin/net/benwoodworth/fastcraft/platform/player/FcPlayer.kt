package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*

interface FcPlayer {
    val username: String
    var customName: String?
    val uuid: UUID
    val locale: FcLocale
    val isOnline: Boolean
    val inventory: FcPlayerInventory

    fun sendMessage(message: FcText)

    fun hasPermission(permission: String): Boolean

    fun giveItems(items: List<FcItem>, dropAll: Boolean)

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
