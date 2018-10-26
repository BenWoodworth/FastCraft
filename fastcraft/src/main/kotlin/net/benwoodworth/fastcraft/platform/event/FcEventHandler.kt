package net.benwoodworth.fastcraft.platform.event

interface FcEventHandler<TEvent : FcEvent> {

    fun handle(Event: TEvent)
}
