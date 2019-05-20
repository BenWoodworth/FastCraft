package net.benwoodworth.fastcraft.platform.server

import java.util.*

interface FcPlayerProvider {

    fun getOnlinePlayers(): List<FcPlayer>

    fun getPlayer(uuid: UUID): FcPlayer?
}
