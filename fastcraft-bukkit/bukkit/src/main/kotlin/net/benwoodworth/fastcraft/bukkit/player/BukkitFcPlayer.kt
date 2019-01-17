package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.entity.Player

interface BukkitFcPlayer : FcPlayer {

    val player: Player
}
