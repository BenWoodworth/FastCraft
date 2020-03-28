package net.benwoodworth.fastcraft.platform.server

interface FcTask {
    val isCancelled: Boolean

    fun cancel()

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
