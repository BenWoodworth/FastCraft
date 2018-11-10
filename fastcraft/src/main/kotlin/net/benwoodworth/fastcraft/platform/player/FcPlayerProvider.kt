package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.util.Extensible
import java.util.*

interface FcPlayerProvider : Extensible {

    fun getOnlinePlayers(): List<FcPlayer>

    fun getPlayer(uuid: UUID): FcPlayer?
}
