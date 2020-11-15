package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask

interface BukkitFcTask : FcTask {
    val taskId: Int

    interface Factory : FcTask.Factory
}

val FcTask.taskId: Int
    get() = (this as BukkitFcTask).taskId
