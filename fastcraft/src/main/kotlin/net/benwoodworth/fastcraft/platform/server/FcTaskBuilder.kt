package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

interface FcTaskBuilder : Extensible {

    fun build(): FcTask

    fun action(value: (task: FcTask) -> Unit): FcTaskBuilder

    fun async(): FcTaskBuilder

    fun tickDelay(value: Long): FcTaskBuilder

    fun tickInterval(value: Long): FcTaskBuilder
}
