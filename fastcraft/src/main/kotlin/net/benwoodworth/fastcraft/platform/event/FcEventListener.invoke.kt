package net.benwoodworth.fastcraft.platform.event

operator fun <TEvent : FcEvent> FcEventListener<TEvent>.invoke(
    handler: FcEventHandler<TEvent>
) {
    subscribe(handler)
}