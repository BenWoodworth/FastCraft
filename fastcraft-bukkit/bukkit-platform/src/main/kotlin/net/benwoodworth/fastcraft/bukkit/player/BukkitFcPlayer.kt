package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.entity.Player

interface BukkitFcPlayer : FcPlayer {
    val player: Player

    interface Provider : FcPlayer.Provider {
        fun getPlayer(player: Player): FcPlayer
    }
}

val FcPlayer.player: Player
    get() = (this as BukkitFcPlayer).player

fun FcPlayer.Provider.getPlayer(player: Player): FcPlayer {
    return (this as BukkitFcPlayer.Provider).getPlayer(player)
}
