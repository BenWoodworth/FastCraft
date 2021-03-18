package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.entity.Player

object FcPlayer_Bukkit {
    interface Operations : FcPlayer.Operations {
        val FcPlayer.player: Player
    }

    interface Provider : FcPlayer.Provider {
        fun getPlayer(player: Player): FcPlayer
    }
}

val FcPlayer.Operations.bukkit: FcPlayer_Bukkit.Operations
    get() = this as FcPlayer_Bukkit.Operations

fun FcPlayer.Provider.getPlayer(player: Player): FcPlayer {
    return (this as FcPlayer_Bukkit.Provider).getPlayer(player)
}
