package net.benwoodworth.fastcraft.platform.event

interface FcEventListener<TEvent : FcEvent> {

    fun subscribe(handler: FcEventHandler<TEvent>)

    fun unsubscribe(handler: FcEventHandler<TEvent>)
}


