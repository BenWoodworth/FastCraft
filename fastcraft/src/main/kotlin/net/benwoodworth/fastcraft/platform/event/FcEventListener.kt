package net.benwoodworth.fastcraft.platform.event

import net.benwoodworth.fastcraft.util.Extensible

interface FcEventListener<TEvent : FcEvent> : Extensible {

    fun subscribe(handler: FcEventHandler<TEvent>)

    fun unsubscribe(handler: FcEventHandler<TEvent>)
}


