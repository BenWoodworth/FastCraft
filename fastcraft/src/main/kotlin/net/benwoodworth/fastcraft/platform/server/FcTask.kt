package net.benwoodworth.fastcraft.platform.server

interface FcTask {
    val isScheduled: Boolean

    fun schedule()

    fun cancel()

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
