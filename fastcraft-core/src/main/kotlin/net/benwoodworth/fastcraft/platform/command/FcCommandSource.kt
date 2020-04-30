package net.benwoodworth.fastcraft.platform.command

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcCommandSource {
    val player: FcPlayer?
    val isConsole: Boolean

    fun hasPermission(permission: String): Boolean

    fun sendMessage(message: FcText)
}
