package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.entity.Player

object BukkitFcPlayer {
    interface TypeClass : FcPlayer.TypeClass {
        val FcPlayer.player: Player
    }

    interface Provider : FcPlayer.Provider {
        fun getPlayer(player: Player): FcPlayer
    }
}

val FcPlayer.TypeClass.bukkit: BukkitFcPlayer.TypeClass
    get() = this as BukkitFcPlayer.TypeClass

fun FcPlayer.Provider.getPlayer(player: Player): FcPlayer {
    return (this as BukkitFcPlayer.Provider).getPlayer(player)
}
