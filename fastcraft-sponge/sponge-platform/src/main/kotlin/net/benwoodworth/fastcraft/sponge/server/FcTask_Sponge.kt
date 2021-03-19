package net.benwoodworth.fastcraft.sponge.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.spongepowered.api.scheduler.Task

interface FcTask_Sponge : FcTask {
    val task: Task

    interface Factory : FcTask.Factory
}

val FcTask.task: Task
    get() = (this as FcTask_Sponge).task
