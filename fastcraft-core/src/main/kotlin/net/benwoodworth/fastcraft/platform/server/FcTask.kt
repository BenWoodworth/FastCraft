package net.benwoodworth.fastcraft.platform.server

interface FcTask {
    val isCancelled: Boolean

    fun cancel()

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        fun startTask(
            async: Boolean = false,
            delaySeconds: Double = 0.0,
            intervalSeconds: Double = 0.0,
            action: (task: FcTask) -> Unit,
        ): FcTask
    }
}
