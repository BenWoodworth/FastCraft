package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask

interface BukkitFcTask : FcTask {
    val taskId: Int

    companion object {
        val FcTask.taskId: Int
            get() = (this as BukkitFcTask).taskId
    }

    interface Factory : FcTask.Factory
}
