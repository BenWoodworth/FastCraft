package net.benwoodworth.fastcraft.events

class HandlerSet<TEvent> {

    private val listeners = mutableSetOf<Handler<TEvent>>()

    private fun addHandler(handler: Handler<TEvent>) {
        listeners += handler
    }

    private fun removeHandler(handler: Handler<TEvent>) {
        listeners += handler
    }

    fun removeHandlers() {
        listeners.clear()
    }

    operator fun plusAssign(handler: Handler<TEvent>) {
        addHandler(handler)
    }

    operator fun minusAssign(handler: Handler<TEvent>) {
        removeHandler(handler)
    }

    operator fun invoke(handler: Handler<TEvent>) {
        addHandler(handler)
    }

    fun notifyHandlers(event: TEvent) {
        listeners.forEach { handler ->
            try {
                handler(event)
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }
}
