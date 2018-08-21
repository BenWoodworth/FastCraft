package net.benwoodworth.fastcraft.platform.event

/**
 * Listens to events, and notifies event handlers when events occur.
 *
 * @param T the type of event being listened for.
 */
class FcListener<T> {

    /**
     * Event handlers with an event parameter.
     */
    private val handlersParam = mutableListOf<(event: T) -> Unit>()

    /**
     * Event handlers without an event parameter.
     */
    private val handlersNoParam = mutableListOf<() -> Unit>()

    /**
     * Raise an event.
     *
     * @param event the event to raise.
     */
    fun notifyHandlers(event: T) {
        handlersParam.forEach {
            try {
                it(event)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        handlersNoParam.forEach {
            try {
                it()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event.
     */
    fun addHandler(handler: (event: T) -> Unit) {
        handlersParam += handler
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event.
     */
    fun addHandler(handler: () -> Unit) {
        handlersNoParam += handler
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event.
     */
    operator fun plusAssign(handler: (event: T) -> Unit) {
        addHandler(handler)
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event.
     */
    operator fun plusAssign(handler: () -> Unit) {
        addHandler(handler)
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener.
     */
    fun removeHandler(handler: (event: T) -> Unit) {
        handlersParam -= handler
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener.
     */
    fun removeHandler(handler: () -> Unit) {
        handlersNoParam -= handler
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener.
     */
    operator fun minusAssign(handler: (event: T) -> Unit) {
        removeHandler(handler)
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener.
     */
    operator fun minusAssign(handler: () -> Unit) {
        removeHandler(handler)
    }
}
