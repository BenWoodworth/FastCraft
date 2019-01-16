package net.benwoodworth.fastcraft.platform.player

import java.util.*

interface FcPlayerProvider {

    fun getOnlinePlayers(): List<FcPlayer>

    fun getPlayer(uuid: UUID): FcPlayer?
}
