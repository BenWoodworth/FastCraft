package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPlayerProvider
import org.bukkit.entity.Player

interface BukkitFcPlayerProvider : FcPlayerProvider {
    fun getPlayer(player: Player): FcPlayer
}

fun FcPlayerProvider.getPlayer(player: Player): FcPlayer {
    return (this as BukkitFcPlayerProvider).getPlayer(player)
}
