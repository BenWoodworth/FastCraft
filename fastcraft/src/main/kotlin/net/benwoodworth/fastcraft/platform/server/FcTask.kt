package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

interface FcTask : Extensible {

    val isScheduled: Boolean

    fun schedule()

    fun cancel()
}
