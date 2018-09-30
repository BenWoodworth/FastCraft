package net.benwoodworth.fastcraft.platform.bukkit.event

import net.benwoodworth.fastcraft.platform.bukkit.player.`BukkitFcPlayer$1_13_R0_1`
import org.bukkit.event.player.PlayerJoinEvent

@Suppress("ClassName")
inline class `BukkitFcEventPlayerJoin$1_13_R0_1`(
    private val event: PlayerJoinEvent
) : BukkitFcEventPlayerJoin {

    override val player: `BukkitFcPlayer$1_13_R0_1`
        get() = `BukkitFcPlayer$1_13_R0_1`(event.player)
}
