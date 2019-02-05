package net.benwoodworth.fastcraft.platform.event

interface FcEventListener<TEvent : FcEvent> {

    operator fun plusAssign(handler: (event: TEvent, listener: FcEventListener<TEvent>) -> Unit)

    operator fun minusAssign(handler: (event: TEvent, listener: FcEventListener<TEvent>) -> Unit)
}
