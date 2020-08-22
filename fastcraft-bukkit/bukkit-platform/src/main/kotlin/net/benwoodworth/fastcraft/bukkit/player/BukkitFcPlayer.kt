package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.entity.Player

object BukkitFcPlayer {
    interface Operations : FcPlayer.Operations {
        val FcPlayer.player: Player
    }

    interface Provider : FcPlayer.Provider {
        fun getPlayer(player: Player): FcPlayer
    }
}

val FcPlayer.Operations.bukkit: BukkitFcPlayer.Operations
    get() = this as BukkitFcPlayer.Operations

fun FcPlayer.Provider.getPlayer(player: Player): FcPlayer {
    return (this as BukkitFcPlayer.Provider).getPlayer(player)
}
