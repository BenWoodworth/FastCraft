package net.benwoodworth.fastcraft.platform.event

import net.benwoodworth.fastcraft.util.Extensible

interface FcEventHandler<TEvent : FcEvent> : Extensible {

    fun handle(Event: TEvent)
}
