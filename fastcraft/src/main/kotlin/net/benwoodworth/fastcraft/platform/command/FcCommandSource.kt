package net.benwoodworth.fastcraft.platform.command

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.platform.text.FcText
import java.util.*

interface FcCommandSource {
    val player: FcPlayer?
    val isConsole: Boolean
    val locale: Locale

    fun hasPermission(permission: FcPermission): Boolean

    fun sendMessage(message: FcText)
}
