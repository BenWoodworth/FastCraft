package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.entity.Player

interface BukkitFcPlayerProvider : FcPlayerProvider {

    fun getPlayer(player: Player): FcPlayer
}
