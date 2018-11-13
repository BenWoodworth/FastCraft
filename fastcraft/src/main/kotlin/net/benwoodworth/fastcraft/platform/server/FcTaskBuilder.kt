package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Builder
import net.benwoodworth.fastcraft.util.Extensible

interface FcTaskBuilder : Extensible, Builder<FcTask> {

    var action: (task: FcTask) -> Unit

    var async: Boolean

    var delaySeconds: Double?

    var intervalSeconds: Double?
}

fun FcTaskBuilder.action(value: (task: FcTask) -> Unit) {
    action = value
}
