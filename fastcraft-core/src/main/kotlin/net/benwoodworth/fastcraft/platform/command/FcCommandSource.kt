package net.benwoodworth.fastcraft.platform.command

import net.benwoodworth.fastcraft.platform.player.FcPlayer

sealed class FcCommandSource {
    abstract class Player : FcCommandSource() {
        abstract val player: FcPlayer
    }

    abstract class Console : FcCommandSource()
}
