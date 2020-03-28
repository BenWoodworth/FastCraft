package net.benwoodworth.fastcraft.platform.server

interface FcTaskFactory {
    fun startTask(
        async: Boolean = false,
        delaySeconds: Double = 0.0,
        intervalSeconds: Double = 0.0,
        action: (task: FcTask) -> Unit
    ): FcTask
}
