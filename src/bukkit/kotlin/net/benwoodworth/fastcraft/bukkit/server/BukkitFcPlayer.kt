package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPlayer
import org.bukkit.entity.Player

interface BukkitFcPlayer : FcPlayer {
    val player: Player
}

val FcPlayer.player: Player
    get() = (this as BukkitFcPlayer).player
