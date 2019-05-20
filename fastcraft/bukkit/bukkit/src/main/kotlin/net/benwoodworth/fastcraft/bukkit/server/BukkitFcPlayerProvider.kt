package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPlayerProvider
import org.bukkit.entity.Player

interface BukkitFcPlayerProvider : FcPlayerProvider {

    fun getPlayer(player: Player): FcPlayer
}
