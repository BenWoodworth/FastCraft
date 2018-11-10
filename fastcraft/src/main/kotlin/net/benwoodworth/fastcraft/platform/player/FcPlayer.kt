package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible
import java.util.*

interface FcPlayer : Extensible {

    val username: String

    var customName: String?

    val uuid: UUID

    fun sendMessage(message: FcText)

    fun hasPermission(permission: String): Boolean
}
