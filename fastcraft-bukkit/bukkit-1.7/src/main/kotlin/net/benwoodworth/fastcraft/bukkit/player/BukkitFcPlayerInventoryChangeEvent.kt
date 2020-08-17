package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventoryChangeEvent

class BukkitFcPlayerInventoryChangeEvent(
    override val player: FcPlayer,
) : FcPlayerInventoryChangeEvent
