package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask

interface FcTask_Bukkit : FcTask {
    val taskId: Int

    interface Factory : FcTask.Factory
}

val FcTask.taskId: Int
    get() = (this as FcTask_Bukkit).taskId
