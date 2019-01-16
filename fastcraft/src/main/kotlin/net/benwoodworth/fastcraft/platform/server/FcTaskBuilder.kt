package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Builder

interface FcTaskBuilder : Builder<FcTask> {

    var action: (task: FcTask) -> Unit

    var async: Boolean

    var delaySeconds: Double?

    var intervalSeconds: Double?
}

fun FcTaskBuilder.action(value: (task: FcTask) -> Unit) {
    action = value
}
