package net.benwoodworth.fastcraft.platform.player

interface FcPlayerOpenWorkbenchEvent {
    val player: FcPlayer

    fun cancel()
}
