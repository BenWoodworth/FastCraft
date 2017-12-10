package net.benwoodworth.fastcraft.dependencies.abstractions.event

/**
 * Listens to events, and notifies event handlers when events occur.
 *
 * @param TEvent the type of event being listened for
 */
class Listener<TEvent> {

    /**
     * Event handlers with an event parameter.
     */
    private val handlersParam = mutableListOf<(event: TEvent) -> Unit>()

    /**
     * Event handlers without an event parameter.
     */
    private val handlersNoParam = mutableListOf<() -> Unit>()

    /**
     * Raise an event.
     *
     * @param event the event to raise
     */
    fun notifyHandlers(event: TEvent) {
        handlersParam.forEach { it(event) }
        handlersNoParam.forEach { it() }
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    fun addHandler(handler: (event: TEvent) -> Unit) {
        handlersParam += handler
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    fun addHandler(handler: () -> Unit) {
        handlersNoParam += handler
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    operator fun plusAssign(handler: (event: TEvent) -> Unit) {
        addHandler(handler)
    }

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    operator fun plusAssign(handler: () -> Unit) {
        addHandler(handler)
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    fun removeHandler(handler: (event: TEvent) -> Unit) {
        handlersParam -= handler
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    fun removeHandler(handler: () -> Unit) {
        handlersNoParam -= handler
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    operator fun minusAssign(handler: (event: TEvent) -> Unit) {
        removeHandler(handler)
    }

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    operator fun minusAssign(handler: () -> Unit) {
        removeHandler(handler)
    }
}
