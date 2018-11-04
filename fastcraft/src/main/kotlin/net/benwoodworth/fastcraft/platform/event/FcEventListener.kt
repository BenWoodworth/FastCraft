package net.benwoodworth.fastcraft.platform.event

import net.benwoodworth.fastcraft.util.Extensible

interface FcEventListener<TEvent : FcEvent> : Extensible {

    fun subscribe(handler: (event: TEvent) -> Unit): FcEventHandler<TEvent>

    fun subscribe(handler: FcEventHandler<TEvent>): FcEventHandler<TEvent>

    fun unsubscribe(handler: FcEventHandler<TEvent>)

    operator fun invoke(handler: (event: TEvent) -> Unit): FcEventHandler<TEvent> {
        return subscribe(handler)
    }

    operator fun invoke(handler: FcEventHandler<TEvent>): FcEventHandler<TEvent> {
        return subscribe(handler)
    }
}


