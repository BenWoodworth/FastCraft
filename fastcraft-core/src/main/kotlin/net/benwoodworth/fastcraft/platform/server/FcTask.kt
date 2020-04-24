package net.benwoodworth.fastcraft.platform.server

interface FcTask {
    val isCancelled: Boolean

    fun cancel()

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        fun startTask(
            async: Boolean = false,
            delayTicks: Long = 0L,
            intervalTicks: Long = 0L,
            action: (task: FcTask) -> Unit,
        ): FcTask
    }
}
