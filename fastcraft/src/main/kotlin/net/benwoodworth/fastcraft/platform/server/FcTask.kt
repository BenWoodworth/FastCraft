package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

interface FcTask : Extensible {

    val isRunning: Boolean

    fun start()

    fun stop()
}
