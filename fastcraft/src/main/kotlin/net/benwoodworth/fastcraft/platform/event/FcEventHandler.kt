package net.benwoodworth.fastcraft.platform.event

typealias FcEventHandler<TEvent> = (event: TEvent, listener: FcEventListener<TEvent>) -> Unit
