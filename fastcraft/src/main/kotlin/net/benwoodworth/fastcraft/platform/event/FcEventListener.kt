package net.benwoodworth.fastcraft.platform.event

interface FcEventListener<TEvent : FcEvent> {

    fun subscribe(handler: (event: TEvent) -> Unit): FcEventHandler<TEvent>

    fun subscribe(handler: FcEventHandler<TEvent>): FcEventHandler<TEvent>

    fun unsubscribe(handler: FcEventHandler<TEvent>)

    operator fun invoke(handler: (event: TEvent) -> Unit): FcEventHandler<TEvent> {
        return subscribe(handler)
    }

    operator fun invoke(handler: FcEventHandler<TEvent>): FcEventHandler<TEvent> {
        return subscribe(handler)
    }

    operator fun plusAssign(handler: FcEventHandler<TEvent>) {
        subscribe(handler)
    }

    operator fun minusAssign(handler: FcEventHandler<TEvent>) {
        unsubscribe(handler)
    }
}


