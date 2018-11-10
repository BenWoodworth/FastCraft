package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

interface FcTaskBuilder : Extensible {

    fun action(value: (task: FcTask) -> Unit): FcTaskBuilder

    interface WithAction : FcTaskBuilder {

        fun async(value: Boolean = true): FcTaskBuilder

        fun tickDelay(value: Long): FcTaskBuilder

        fun tickInterval(value: Long): FcTaskBuilder

        fun build(): FcTask
    }
}
